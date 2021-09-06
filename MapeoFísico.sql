CREATE DATABASE MiMuebleria;
USE MiMuebleria;

CREATE USER 'UsuarioProyecto'@'localhost' IDENTIFIED BY '[Costumbre1]';
GRANT ALL PRIVILEGES ON MiMuebleria.* TO 'UsuarioProyecto'@'localhost';

CREATE TABLE cliente(
	nit VARCHAR(12) NOT NULL,
	direccion VARCHAR(100) NOT NULL,
	municipio VARCHAR(100),
	departamento VARCHAR(100),
	nombre VARCHAR(45) NOT NULL,
	CONSTRAINT PK_CLIENTE PRIMARY KEY (nit)
);

CREATE TABLE pieza(
	tipo VARCHAR(45) NOT NULL,
	costo DOUBLE NOT NULL,
	cantidad INT NOT NULL,
	CONSTRAINT PK_PIEZA PRIMARY KEY(tipo, costo)
);

CREATE TABLE usuario(
	nombre_usuario VARCHAR(45) NOT NULL,
	contraseña VARCHAR(45) NOT NULL,
	tipo_usuario INT NOT NULL,
	CONSTRAINT PK_USUARIO PRIMARY KEY(nombre_usuario)
);

CREATE TABLE mueble(
	nombre_mueble VARCHAR(75) NOT NULL,
    precio DOUBLE NOT NULL,
    CONSTRAINT PK_MUEBLE PRIMARY KEY(nombre_mueble)
);

CREATE TABLE ensamble_piezas(
	mueble_nombre VARCHAR(75) NOT NULL,
    pieza_tipo VARCHAR(45) NOT NULL,
	cantidad INT NOT NULL,
	CONSTRAINT FK_TO_MUEBLE FOREIGN KEY (mueble_nombre) REFERENCES mueble(nombre_mueble),
	CONSTRAINT FK_TO_PIEZA FOREIGN KEY(pieza_tipo) REFERENCES pieza(tipo)
);

CREATE TABLE mueble_ensamblado(
	identificador_mueble VARCHAR(6) NOT NULL,
	fecha_ensamblaje DATE,
	precio DOUBLE,
	costo_construccion DOUBLE NOT NULL,
	estado INT NOT NULL,
	usuario_constructor VARCHAR(45),
	nombre_mueble_ensamble VARCHAR(75) NOT NULL,
	CONSTRAINT PK_MUEBLE_ENSAMBLADO PRIMARY KEY (identificador_mueble),
	CONSTRAINT FK_TO_MUEBLE_NOMBRE FOREIGN KEY (nombre_mueble_ensamble) REFERENCES mueble (nombre_mueble),
	CONSTRAINT FK_TO_USUARIO FOREIGN KEY (usuario_constructor) REFERENCES usuario(nombre_usuario)
);

CREATE TABLE venta(
	id_venta INT NOT NULL AUTO_INCREMENT,
	total DOUBLE NOT NULL,
	fecha_compra DATE NOT NULL,
	nit_cliente VARCHAR(12) NOT NULL,
	usuario_vendedor VARCHAR(45) NOT NULL,
	numeros_serie VARCHAR(244),
	CONSTRAINT PK_VENTA PRIMARY KEY (id_venta),
	CONSTRAINT FK_TO_CLIENTE FOREIGN KEY (nit_cliente) REFERENCES cliente (nit),
	CONSTRAINT FK_TO_USUARIO_VENDEDOR FOREIGN KEY (usuario_vendedor) REFERENCES usuario(nombre_usuario)
);

CREATE TABLE detalle_venta(
	id_detalle_venta INT NOT NULL AUTO_INCREMENT,
	venta_id INT NOT NULL,
	mueble_identificador_mueble VARCHAR(6) NOT NULL,
	precio_venta DOUBLE NULL,
	CONSTRAINT PK_DETALLE_VENTA PRIMARY KEY (id_detalle_venta),
	CONSTRAINT FK_TO_MUEBLE_ENSAMBLADO FOREIGN KEY (mueble_identificador_mueble) REFERENCES mueble_ensamblado(identificador_mueble),
	CONSTRAINT FK_TO_VENTA FOREIGN KEY (venta_id) REFERENCES venta(id_venta)
);

CREATE TABLE devolucion(
	no_devolucion INT NOT NULL AUTO_INCREMENT,
	fecha_devolucion DATE NOT NULL,
	perdida DOUBLE NOT NULL,
	numeros_serie VARCHAR(244),
	CONSTRAINT PK_DEVOLUCION PRIMARY KEY (no_devolucion)
); 

#Querys del area de fabrica#
SELECT tipo AS 'TIPO DE PIEZA', costo as 'COSTO DE LA PIEZA', cantidad as 'CANTIDAD' FROM pieza;
SELECT tipo AS 'TIPO DE PIEZA', costo as 'COSTO DE LA PIEZA', cantidad as 'CANTIDAD' FROM pieza ORDER BY cantidad DESC;
SELECT tipo AS 'TIPO DE PIEZA', costo as 'COSTO DE LA PIEZA', cantidad as 'CANTIDAD' FROM pieza ORDER BY cantidad ASC;
SELECT identificador_mueble AS 'IDENTIFICADOR', nombre_mueble_ensamble AS 'NOMBRE DE MUEBLE', usuario_constructor AS 'USUARIO ENSAMBLADOR', precio AS 'COSTO', fecha_ensamblaje AS 'FECHA DE ENSAMBLAJE' FROM mueble WHERE estado=2;
SELECT identificador_mueble AS 'IDENTIFICADOR', nombre_mueble_ensamble AS 'NOMBRE DE MUEBLE', usuario_constructor AS 'USUARIO ENSAMBLADOR', precio AS 'COSTO', fecha_ensamblaje AS 'FECHA DE ENSAMBLAJE' FROM mueble WHERE estado=2 ORDER BY fecha_ensamblaje DESC;
SELECT identificador_mueble AS 'IDENTIFICADOR', nombre_mueble_ensamble AS 'NOMBRE DE MUEBLE', usuario_constructor AS 'USUARIO ENSAMBLADOR', precio AS 'COSTO', fecha_ensamblaje AS 'FECHA DE ENSAMBLAJE' FROM mueble WHERE estado=2 ORDER BY fecha_ensamblaje ASC;

#Querys del area de puntos de venta#
SELECT * FROM mueble_ensamblado;
SELECT c.nombre, c.direccion, c.nit, v.fecha_compra ,dv.mueble_identificador_mueble, m.nombre_mueble_ensamble FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE Upper(c.nombre) LIKE UPPER('Yendi'); 
SELECT c.nombre, c.direccion, c.nit, v.fecha_compra ,dv.mueble_identificador_mueble, m.nombre_mueble_ensamble FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE Upper(c.nombre) LIKE UPPER('Yendi') AND v.fecha_compra BETWEEN '2011/02/25' AND '2021/09/05'; 
SELECT v.numeros_serie, d.fecha_devolucion, c.nombre, c.nit, m.nombre_mueble_ensamble, d.perdida FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN devolucion d ON (d.numeros_serie=v.numeros_serie) JOIN detalle_venta dv ON (dv.venta_id=v.id_venta) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE Upper(c.nombre) LIKE UPPER('Luis Arturo'); 
SELECT v.numeros_serie, d.fecha_devolucion, c.nombre, c.nit, m.nombre_mueble_ensamble, d.perdida FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN devolucion d ON (d.numeros_serie=v.numeros_serie) JOIN detalle_venta dv ON (dv.venta_id=v.id_venta) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE Upper(c.nombre) LIKE UPPER('Luis Arturo') AND d.fecha_devolucion BETWEEN '2011/02/25' AND '2021/09/05'; 
SELECT m.identificador_mueble AS 'IDENTIFICADOR', m.nombre_mueble_ensamble AS 'MUEBLE', m.precio AS 'PRECIO' FROM mueble_ensamblado m WHERE m.estado=3;
SELECT v.numeros_serie, v.fecha_compra, c.nombre, c.direccion, c.nit, dv.mueble_identificador_mueble, m.nombre_mueble_ensamble, v.total FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE c.nombre LIKE 'Yendi';
SELECT m.identificador_mueble, m.nombre_mueble_ensamble, m.precio, v.fecha_compra FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.fecha_compra='2021/09/03';

#Querys del area financiera y administración#
SELECT m.identificador_mueble, m.nombre_mueble_ensamble, m.precio, v.fecha_compra FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble);
SELECT m.identificador_mueble, m.nombre_mueble_ensamble, m.precio, v.fecha_compra FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.fecha_compra BETWEEN '2011/02/25' AND '2021/09/04';
SELECT v.numeros_serie, d.fecha_devolucion, c.nombre, c.nit, m.nombre_mueble_ensamble, d.perdida FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN devolucion d ON (d.numeros_serie=v.numeros_serie) JOIN detalle_venta dv ON (dv.venta_id=v.id_venta) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble);
SELECT v.numeros_serie, d.fecha_devolucion, c.nombre, c.nit, m.nombre_mueble_ensamble, d.perdida FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN devolucion d ON (d.numeros_serie=v.numeros_serie) JOIN detalle_venta dv ON (dv.venta_id=v.id_venta) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE d.fecha_devolucion BETWEEN '2011/02/25' AND '2021/09/05';
SELECT SUM(v.total) AS 'TOTAL' FROM venta v;
SELECT m.identificador_mueble, m.nombre_mueble_ensamble, m.precio mueble_ensamblado FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble);
SELECT SUM(v.total) AS 'TOTAL' FROM venta v WHERE v.fecha_compra BETWEEN '2011/02/25' AND '2021/09/05';
SELECT m.identificador_mueble, m.nombre_mueble_ensamble, m.precio mueble_ensamblado FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE v.fecha_compra BETWEEN '2011/02/25' AND '2021/09/05';
SELECT c.nombre FROM venta v JOIN cliente c ON v.nit_cliente=c.nit GROUP BY v.nit_cliente ORDER BY COUNT(v.nit_cliente) DESC LIMIT 1;
SELECT c.nombre FROM venta v JOIN cliente c ON v.nit_cliente=c.nit GROUP BY v.nit_cliente, v.fecha_compra BETWEEN '2011/02/25' AND '2021/09/05' ORDER BY COUNT(v.nit_cliente) DESC LIMIT 1;
SELECT c.nombre, dv.mueble_identificador_mueble, m.nombre_mueble_ensamble, m.precio FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE c.nombre LIKE 'Yendi';
SELECT c.nombre FROM venta v JOIN cliente c ON (v.nit_cliente=c.nit) GROUP BY c.nombre ORDER BY SUM(v.total) DESC LIMIT 1;
SELECT c.nombre FROM venta v JOIN cliente c ON (v.nit_cliente=c.nit) GROUP BY v.fecha_compra BETWEEN '2011/02/25' AND '2021/09/04', c.nombre ORDER BY SUM(v.total) DESC LIMIT 1;
SELECT c.nombre, dv.mueble_identificador_mueble, m.nombre_mueble_ensamble, m.precio FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE c.nombre LIKE 'Luis Arturo';
SELECT m.nombre_mueble_ensamble FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) GROUP BY dv.mueble_identificador_mueble ORDER BY COUNT(dv.mueble_identificador_mueble) DESC LIMIT 1;
SELECT m.nombre_mueble_ensamble FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) GROUP BY dv.mueble_identificador_mueble, v.fecha_compra BETWEEN '2011/02/25' AND '2021/09/04' ORDER BY COUNT(dv.mueble_identificador_mueble) DESC LIMIT 1;
SELECT v.numeros_serie, c.nombre, c.direccion, dv.mueble_identificador_mueble, m.nombre_mueble_ensamble, m.precio FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE m.nombre_mueble_ensamble LIKE 'Mesa rustica';
SELECT m.nombre_mueble_ensamble FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE m.identificador_mueble=(SELECT MIN(m.identificador_mueble)  FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble));
SELECT m.nombre_mueble_ensamble FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE (v.fecha_compra BETWEEN '2011/02/25' AND '2021/09/04') AND (m.identificador_mueble=(SELECT MIN(m.identificador_mueble)  FROM venta v JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble)));
SELECT v.numeros_serie, c.nombre, c.direccion, dv.mueble_identificador_mueble, m.nombre_mueble_ensamble, m.precio FROM cliente c JOIN venta v ON (c.nit=v.nit_cliente) JOIN detalle_venta dv ON (v.id_venta=dv.venta_id) JOIN mueble_ensamblado m ON(dv.mueble_identificador_mueble=m.identificador_mueble) WHERE m.nombre_mueble_ensamble LIKE 'Mesa rustica';