<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<section class="w-f scrollable">
	<div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 100%;">
		<div data-color="#333333" data-size="5px" data-distance="0" data-disable-fade-out="true" data-height="auto" class="slim-scroll" style="overflow: hidden; width: auto; height: 100%;">
			<!-- nav -->
			<nav class="nav-primary hidden-xs">
				<ul id="nav" class="nav">
					<sec:authorize access="hasAnyRole('ROLE_SA','ROLE_ADMIN')">
						<li><a href="#"> <i class="fa fa-dashboard icon"> <b class="bg-danger"></b>
							</i> <span class="pull-right"> <i class="fa fa-angle-down text"></i> <i class="fa fa-angle-up text-active"></i>
							</span> <span><spring:message code="master" /></span>
						</a>
							<ul class="nav lt">
								<sec:authorize access="hasRole('ROLE_SA')">
									<li><a id="nav_official_list" href="<c:url value="/official/"/>"> <i class="fa fa-angle-right"></i><span><spring:message code="official.list" /></span>
									</a></li>
								</sec:authorize>
								<sec:authorize access="hasAnyRole('ROLE_SA','ROLE_ADMIN')">
									<li><a id="nav_user_list" href="<c:url value="/user/"/>"> <i class="fa fa-angle-right"></i> <span><spring:message code="user.list" /></span>
									</a></li>
								</sec:authorize>
								<sec:authorize access="hasRole('ROLE_SA')">
									<li><a id="nav_participant_list" href="<c:url value="/participant/"/>"> <i class="fa fa-angle-right"></i> <span><spring:message code="participant.list" /></span>
									</a></li>
								</sec:authorize>
								<sec:authorize access="hasRole('ROLE_SA')">
									<li><a id="nav_subdivision_list" href="<c:url value="/subdivision/"/>"> <i class="fa fa-angle-right"></i><span><spring:message code="subdivision.list" /></span>
									</a></li>
								</sec:authorize>
								<sec:authorize access="hasRole('ROLE_SA')">
									<li><a id="nav_position_list" href="<c:url value="/position/"/>"> <i class="fa fa-angle-right"></i><span><spring:message code="position.list" /></span>
									</a></li>
								</sec:authorize>
								<sec:authorize access="hasRole('ROLE_SA')">
									<li><a id="nav_grade_list" href="<c:url value="/grade/"/>"> <i class="fa fa-angle-right"></i><span><spring:message code="grade.list" /></span>
									</a></li>
								</sec:authorize>
							</ul></li>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_SA')">
						<li><a href="#layout"> <i class="fa fa-dashboard icon"> <b class="bg-warning"></b>
							</i> <span class="pull-right"> <i class="fa fa-angle-down text"></i> <i class="fa fa-angle-up text-active"></i>
							</span> <span><span><spring:message code="finance" /></span></a>
							<ul class="nav lt">
								<li><a id="nav_balance_list" href="<c:url value="/balance"/>"> <i class="fa fa-angle-right"></i> <span><spring:message code="balance.list" /></span>
								</a></li>
							</ul>
							<ul class="nav lt">
								<li><a id="nav_account_list" href="<c:url value="/account"/>"> <i class="fa fa-angle-right"></i> <span><spring:message code="account.list" /></span>
								</a></li>
							</ul>
							<ul class="nav lt">
								<li><a id="nav_accountdetail_view_list" href="<c:url value="/accountdetail/view"/>"> <i class="fa fa-angle-right"></i> <span><spring:message code="accountDetail.list" /></span>
								</a></li>
							</ul></li>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_SA')">
						<li><a href="#layout"> <i class="fa fa-columns icon"> <b class="bg-warning"></b>
							</i> <span class="pull-right"> <i class="fa fa-angle-down text"></i> <i class="fa fa-angle-up text-active"></i>
							</span> <span><span><spring:message code="planning" /></span></a>
							<ul class="nav lt">
								<li><a id="nav_activity_list" href="<c:url value="/activity/"/>"> <i class="fa fa-angle-right"></i> <span><spring:message code="activity.list" /></span>
								</a></li>
							</ul></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_SA','ROLE_USER','ROLE_ADMIN')">
						<li><a href="#uikit"> <i class="fa fa-flask icon"> <b class="bg-success"></b>
							</i> <span class="pull-right"> <i class="fa fa-angle-down text"></i> <i class="fa fa-angle-up text-active"></i>
							</span> <span><spring:message code="implementation" /></span>
						</a>
							<ul class="nav lt">
								<sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
									<li><a id="nav_journey_list" href="<c:url value="/journey/activity"/>"> <i class="fa fa-angle-right"></i> <span> <spring:message code="journey.create" />
										</span>
									</a></li>
								</sec:authorize>
								<sec:authorize access="hasRole('ROLE_SA')">
									<li><a id="nav_sa_journey_list" href="<c:url value="/sa/journey"/>"> <i class="fa fa-angle-right"></i> <span><spring:message code="journey.list" /></span>
									</a></li>
									<li><a id="nav_stock_list" href="<c:url value="/stock/activity"/>"> <i class="fa fa-angle-right"></i> <span><spring:message code="stock" /></span>
									</a></li>
									<li><a id="nav_receipt_list" href="<c:url value="/receipt/activity"/>"> <i class="fa fa-angle-right"></i> <span><spring:message code="receipt" /></span>
									</a></li>
									<li><a id="nav_sa_pend_list" href="<c:url value="/sa/pendingcomplete"/>"> <i class="fa fa-angle-right"></i> <span><spring:message code="return.amount.list" /></span>
									</a></li>
								</sec:authorize>
							</ul></li>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_SA')">
						<li><a href="#pages"> <i class="fa fa-file-text icon"> <b class="bg-primary"></b>
							</i> <span class="pull-right"> <i class="fa fa-angle-down text"></i> <i class="fa fa-angle-up text-active"></i>
							</span> <span>Laporan</span>
						</a>
							<ul class="nav lt">
								<li><a id="nav_up" href="<c:url value="/sa/receiptup"/>"> <i class="fa fa-angle-right"></i> <span>Laporan Kuitansi Pembayaran Up</span>
								</a></li>
							</ul></li>
							  
					</sec:authorize>
				</ul>
			</nav>
			<!-- / nav -->
		</div>
		<div class="slimScrollBar" style="background: none repeat scroll 0% 0% rgb(51, 51, 51); width: 5px; position: absolute; top: -275px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 0px; height: 153.783px;"></div>
		<div class="slimScrollRail" style="width: 5px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: none repeat scroll 0% 0% rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 0px;"></div>
	</div>
</section>
