package org.displaytag.render;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.exception.DecoratorException;
import org.displaytag.exception.ObjectLookupException;
import org.displaytag.exception.WrappedRuntimeException;
import org.displaytag.model.Column;
import org.displaytag.model.HeaderCell;
import org.displaytag.model.Row;
import org.displaytag.model.TableModel;
import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.SmartListHelper;
import org.displaytag.properties.MediaTypeEnum;
import org.displaytag.properties.SortOrderEnum;
import org.displaytag.properties.TableProperties;
import org.displaytag.tags.CaptionTag;
import org.displaytag.util.Anchor;
import org.displaytag.util.Href;
import org.displaytag.util.HtmlAttributeMap;
import org.displaytag.util.ParamEncoder;
import org.displaytag.util.PostHref;

public class HtmlTableWriter extends TableWriterAdapter {
	private static Log log = LogFactory.getLog(HtmlTableWriter.class);
	private TableModel tableModel;
	private TableProperties properties;
	private JspWriter out;
	private ParamEncoder paramEncoder;
	private Href baseHref;
	private boolean export;
	private CaptionTag captionTag;
	private PaginatedList paginatedList;
	private SmartListHelper listHelper;
	private int pagesize;
	private HtmlAttributeMap attributeMap;
	private String uid;

	public HtmlTableWriter(TableModel tableModel, TableProperties tableProperties, Href baseHref, boolean export, JspWriter out, CaptionTag captionTag, PaginatedList paginatedList, SmartListHelper listHelper, int pagesize, HtmlAttributeMap attributeMap, String uid) {
		this.tableModel = tableModel;
		this.properties = tableProperties;
		this.baseHref = baseHref;
		this.export = export;
		this.out = out;
		this.captionTag = captionTag;
		this.paginatedList = paginatedList;
		this.listHelper = listHelper;
		this.pagesize = pagesize;
		this.attributeMap = attributeMap;
		this.uid = uid;
	}

	protected void writeTopBanner(TableModel model) {
		if (this.tableModel.getForm() != null) {
			String js = "<script type=\"text/javascript\">\nfunction displaytagform(formname, fields){\n    var objfrm = document.forms[formname];\n    for (j=fields.length-1;j>=0;j--){var f= objfrm.elements[fields[j].f];if (f){f.value=fields[j].v};}\n    objfrm.submit();\n}\n</script>";

			writeFormFields();
			write(js);
		}
		writeSearchResultAndNavigation();
	}

	protected void writeTableOpener(TableModel model) {
		write(getOpenTag());
	}

	private void writeFormFields() {
		Map parameters = this.baseHref.getParameterMap();

		ParamEncoder pe = new ParamEncoder(this.tableModel.getId());

		addIfMissing(parameters, pe.encodeParameterName("o"));
		addIfMissing(parameters, pe.encodeParameterName("p"));
		addIfMissing(parameters, pe.encodeParameterName("s"));

		for (Iterator it = parameters.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			Object value = parameters.get(key);

			if ((value != null & value.getClass().isArray())) {
				Object[] arr = (Object[]) value;
				for (int j = 0; j < arr.length; j++) {
					writeField(key, arr[j]);
				}
			} else {
				writeField(key, value);
			}
		}
	}

	private void writeField(String key, Object value) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<input type=\"hidden\" name=\"");
		buffer.append(esc(key));
		buffer.append("\" value=\"");
		buffer.append(value);
		buffer.append("\"/>");

		write(buffer.toString());
	}

	private String esc(Object value) {
		String valueEscaped = StringUtils.replace(ObjectUtils.toString(value), "\"", "\\\"");
		return valueEscaped;
	}

	private void addIfMissing(Map parameters, String key) {
		if (!parameters.containsKey(key)) {
			parameters.put(key, "");
		}
	}

	protected void writeCaption(TableModel model) {
		write(this.captionTag.getOpenTag() + model.getCaption() + this.captionTag.getCloseTag());
	}

	protected void writePreBodyFooter(TableModel model) {
		write("\n<tfoot>");
		write(model.getFooter());
		write("</tfoot>");
	}

	protected void writeTableBodyOpener(TableModel model) {
		write("\n<tbody>");
	}

	protected void writeTableBodyCloser(TableModel model) {
		write("</tbody>");
	}

	protected void writeTableCloser(TableModel model) {
		write("</");
		write("table");
		write(">");
	}

	protected void writeBottomBanner(TableModel model) {
		writeNavigationAndExportLinks();
	}

	protected void writeDecoratedTableFinish(TableModel model) {
		model.getTableDecorator().finish();
	}

	protected void writeDecoratedRowStart(TableModel model) {
		write(model.getTableDecorator().startRow());
	}

	protected void writeRowOpener(Row row) {
		write(row.getOpenTag());
	}

	protected void writeColumnOpener(Column column) throws ObjectLookupException, DecoratorException {
		write(column.getOpenTag());
	}

	protected void writeColumnCloser(Column column) {
		write(column.getCloseTag());
	}

	protected void writeRowWithNoColumns(String rowValue) {
		write("\n<td>");
		write(rowValue);
		write("</td>");
	}

	protected void writeRowCloser(Row row) {
		write(row.getCloseTag());
	}

	protected void writeDecoratedRowFinish(TableModel model) {
		write(model.getTableDecorator().finishRow());
	}

	protected void writeEmptyListMessage(String emptyListMessage) {
		write(emptyListMessage);
	}

	protected void writeColumnValue(Object value, Column column) {
		write(value);
	}

	protected void writeEmptyListRowMessage(String message) {
		write(message);
	}

	protected void writeTableHeader(TableModel model) {
		if (log.isDebugEnabled()) {
			log.debug("[" + this.tableModel.getId() + "] getTableHeader called");
		}

		write("\n<thead>");

		write("\n<tr>");

		if (this.tableModel.isEmpty()) {
			write("\n<th>");
			write("</th>");
		}

		Iterator iterator = this.tableModel.getHeaderCellList().iterator();

		while (iterator.hasNext()) {
			HeaderCell headerCell = (HeaderCell) iterator.next();
			
			
//			HtmlAttributeMap _attributeMap= new HtmlAttributeMap();
//			_attributeMap.put("data-toggle", "buttons");
//			headerCell.setHeaderAttributes(_attributeMap);
			
			if (headerCell.isAlreadySorted()) {
				headerCell.addHeaderClass(this.properties.getCssSorted());
				headerCell.addHeaderClass(this.properties.getCssOrder(this.tableModel.isSortOrderAscending()));
			} else {
				if (headerCell.getSortable()) {
					String cssSortable = this.properties.getCssSortable();
					headerCell.addHeaderClass(cssSortable);
				}
			}

			write(headerCell.getHeaderOpenTag());

			String header = headerCell.getTitle();

			if (headerCell.getSortable()) {
				Anchor anchor = new Anchor(getSortingHref(headerCell), header);

				header = anchor.toString();
			}

			write(header);
			write(headerCell.getHeaderCloseTag());
		}

		write("</tr>");

		write("</thead>");

		if (log.isDebugEnabled()) {
			log.debug("[" + this.tableModel.getId() + "] getTableHeader end");
		}
	}

	private Href getSortingHref(HeaderCell headerCell) {
		Href href = (Href) this.baseHref.clone();

		if (this.tableModel.getForm() != null) {
			href = new PostHref(href, this.tableModel.getForm());
		}

		if (this.paginatedList == null) {
			if ((!this.tableModel.isLocalSort()) && (headerCell.getSortName() != null)) {
				href.addParameter(encodeParameter("s"), headerCell.getSortName());
				href.addParameter(encodeParameter("n"), "1");
			} else {
				href.addParameter(encodeParameter("s"), headerCell.getColumnNumber());
			}

			boolean nowOrderAscending = true;

			if (headerCell.getDefaultSortOrder() != null) {
				boolean sortAscending = SortOrderEnum.ASCENDING.equals(headerCell.getDefaultSortOrder());
				nowOrderAscending = headerCell.isAlreadySorted() ? false : !this.tableModel.isSortOrderAscending() ? true : sortAscending;
			} else {
				nowOrderAscending = (!headerCell.isAlreadySorted()) || (!this.tableModel.isSortOrderAscending());
			}

			int sortOrderParam = nowOrderAscending ? SortOrderEnum.ASCENDING.getCode() : SortOrderEnum.DESCENDING.getCode();

			href.addParameter(encodeParameter("o"), sortOrderParam);

			if ((this.tableModel.isSortFullTable()) || (!this.tableModel.isLocalSort())) {
				href.addParameter(encodeParameter("p"), 1);
			}
		} else {
			if (this.properties.getPaginationSkipPageNumberInSort()) {
				href.removeParameter(this.properties.getPaginationPageNumberParam());
			}

			String sortProperty = headerCell.getSortProperty();
			if (sortProperty == null) {
				sortProperty = headerCell.getBeanPropertyName();
			}

			href.addParameter(this.properties.getPaginationSortParam(), sortProperty);
			String dirParam;
			if (headerCell.isAlreadySorted()) {
				dirParam = this.tableModel.isSortOrderAscending() ? this.properties.getPaginationDescValue() : this.properties.getPaginationAscValue();
			} else {
				dirParam = this.properties.getPaginationAscValue();
			}
			href.addParameter(this.properties.getPaginationSortDirectionParam(), dirParam);
			if (this.paginatedList.getSearchId() != null) {
				href.addParameter(this.properties.getPaginationSearchIdParam(), this.paginatedList.getSearchId());
			}
		}

		return href;
	}

	private String encodeParameter(String parameterName) {
		if (this.paramEncoder == null) {
			this.paramEncoder = new ParamEncoder(this.tableModel.getId());
		}

		return this.paramEncoder.encodeParameterName(parameterName);
	}

	public void writeNavigationAndExportLinks() {
		
		write("<footer class=\"panel-footer\">");
		if (this.properties.getAddPagingBannerBottom()) {
			writeSearchResultAndNavigation();
		}

		if ((this.export) && (this.tableModel.getRowListPage().size() != 0)) {
			writeExportLinks();
		}
		write("</footer>");
	}

	public void writeSearchResultAndNavigation() {
		
		if (((this.paginatedList == null) && (this.pagesize != 0) && (this.listHelper != null)) || (this.paginatedList != null)) {

			write("<div class=\"row\">");
			
			Href navigationHref = (Href) this.baseHref.clone();

			if (this.tableModel.getForm() != null) {
				navigationHref = new PostHref(navigationHref, this.tableModel.getForm());
			}

			write(this.listHelper.getSearchResultsSummary());
			String pageParameter;
			if (this.paginatedList == null) {
				pageParameter = encodeParameter("p");
			} else {
				pageParameter = this.properties.getPaginationPageNumberParam();
				if ((this.paginatedList.getSearchId() != null) && (!navigationHref.getParameterMap().containsKey(this.properties.getPaginationSearchIdParam()))) {
					navigationHref.addParameter(this.properties.getPaginationSearchIdParam(), this.paginatedList.getSearchId());
				}
			}
			write(this.listHelper.getPageNavigationBar(navigationHref, pageParameter));
			write("</div>");
		}
		
	}

	private void writeExportLinks() {
		Href exportHref = (Href) this.baseHref.clone();

		StringBuffer buffer = new StringBuffer(200);
		Iterator iterator = MediaTypeEnum.iterator();

		while (iterator.hasNext()) {
			MediaTypeEnum currentExportType = (MediaTypeEnum) iterator.next();

			if (this.properties.getAddExport(currentExportType)) {
				if (buffer.length() > 0) {
					buffer.append(this.properties.getExportBannerSeparator());
				}

				exportHref.addParameter(encodeParameter("e"), currentExportType.getCode());

				exportHref.addParameter("6578706f7274", "1");

				Anchor anchor = new Anchor(exportHref, this.properties.getExportLabel(currentExportType));
				buffer.append(anchor.toString());
			}
		}

		String[] exportOptions = { buffer.toString() };
		write(MessageFormat.format(this.properties.getExportBanner(), exportOptions));
	}

	public String getOpenTag() {
		if ((this.uid != null) && (this.attributeMap.get("id") == null)) {
			Map localAttributeMap = (Map) this.attributeMap.clone();
			localAttributeMap.put("id", this.uid);

			StringBuffer buffer = new StringBuffer();
			buffer.append("\n<").append("table");
			buffer.append(localAttributeMap);
			buffer.append(">");

			return buffer.toString();
		}

		StringBuffer buffer = new StringBuffer();

		buffer.append("\n<").append("table");
		buffer.append(this.attributeMap);
		buffer.append(">");

		return buffer.toString();
	}

	public void write(String string) {
		if (string != null) {
			try {
				this.out.write(string);
			} catch (IOException e) {
				throw new WrappedRuntimeException(getClass(), e);
			}
		}
	}

	public void writeTable(TableModel model, String id) throws JspException {
		super.writeTable(model, id);
	}

	public void write(Object string) {
		if (string != null) {
			try {
				this.out.write(string.toString());
			} catch (IOException e) {
				throw new WrappedRuntimeException(getClass(), e);
			}
		}
	}
}