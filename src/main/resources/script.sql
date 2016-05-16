
CREATE DATABASE epu;
USE epu;


	

INSERT IGNORE INTO grade (id, name) VALUES
	('e61d2929-59b8-4107-8939-975df1281f48', 'Penata Tk.1, Gol. III/d'),
	('f19cd7c5-06ff-419e-98fc-f30ef903a6b4', 'Penata Muda Tk.1, Gol. III/b');
	
INSERT IGNORE INTO position (id, name) VALUES
	('013ffa55-0888-4234-ac94-c4ff07924e4a', 'Kasubdit Penilaian Diagnostic Invitro dan PKRT'),
	('b1c38023-e2b6-4865-80a0-79a796ec3cb5', 'Direktur'),
	('c5a31840-610d-40ff-b634-26b1f3ed3215', 'Kasubdit Penilaian Alat Kesehatan');	
	
	
INSERT IGNORE INTO subdivision (code, created_by, created_date, name, updated_by, updated_date) VALUES
	('S-00B1', NULL, NULL, 'Elektromedik', NULL, NULL),
	('S-00B2', NULL, NULL, 'Non Elektromedik', NULL, NULL),
	('S-00B3', NULL, NULL, 'Diagnostic Invitro', NULL, NULL),
	('S-00B4', NULL, NULL, 'PKRT', NULL, NULL),
	('S-00B5', NULL, NULL, 'Proyek', NULL, NULL),
	('S-00B6', NULL, NULL, 'Tata Usaha', NULL, NULL),
	('S-00B7', NULL, NULL, 'Inspeksi', NULL, NULL),
	('S-00B8', NULL, NULL, 'Sertifikasi IPAK', NULL, NULL);	

INSERT IGNORE INTO user (username, enabled, password, role, subdivision_code) VALUES
	('admin', true, '21232f297a57a5a743894a0e4a801fc3', 'ROLE_ADMIN', 'S-00B1'),
	('dony', true, '77ee6d05b23e742e2aca3fd602f4c599', 'ROLE_USER', 'S-00B1'),
	('sa', true, 'c12e01f2a13ff5587e1e9e4aedb8242d', 'ROLE_SA', NULL);
