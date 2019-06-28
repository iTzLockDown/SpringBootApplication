INSERT INTO `xvi_finpaises` (`id`, `nombre_pais`) VALUES (NULL, 'portugal');
INSERT INTO `xvi_finpaises` (`id`, `nombre_pais`) VALUES (NULL, 'peru');
INSERT INTO `xvi_finpaises` (`id`, `nombre_pais`) VALUES (NULL, 'argentina');
INSERT INTO `xvi_finpaises` (`id`, `nombre_pais`) VALUES (NULL, 'peru');
INSERT INTO `xvi_findeportes` (`id`, `descripcion`, `est_activo`, `nombre`) VALUES (NULL, 'juego de 22 jugadores', '1', 'futbol1');
INSERT INTO `xvi_findeportes` (`id`, `descripcion`, `est_activo`, `nombre`) VALUES (NULL, 'juego de 22 jugadores', '1', 'futbol2');
INSERT INTO `xvi_findeportes` (`id`, `descripcion`, `est_activo`, `nombre`) VALUES (NULL, 'juego de 22 jugadores', '1', 'futbol3');

INSERT INTO `xvi_finusuarios` (`id`, `apellidom`, `apellidop`, `direccion`, `dni`, `email`, `est_activo`, `fecha_nac`, `fecha_reg`, `nombre`, `password`, `sexo`, `telefono`, `pais_id`) VALUES (NULL, 'taype1', 'ruiz1', 'av brasil1', '47418891', 'wilhelm1@gmail.com', 1, '2019-05-01', '2019-05-21', 'wilhelm1', '$2a$10$mo3kbBJbNxrGDX3mDUC7ouW7nirAsQ7hJXUBZb3Cim8XSLfVLfnFm', 'M', '930248341', '1');
INSERT INTO `xvi_finusuarios` (`id`, `apellidom`, `apellidop`, `direccion`, `dni`, `email`, `est_activo`, `fecha_nac`, `fecha_reg`, `nombre`, `password`, `sexo`, `telefono`, `pais_id`) VALUES (NULL, 'taype2', 'ruiz2', 'av brasil2', '47418892', 'wilhelm2@gmail.com', 1, '2019-05-02', '2019-05-22', 'wilhelm2', '$2a$10$/als.3LSQ05sWTjELdOIA.zpUV4yQq5pM0H/VoXbmfsAeVPEe9nce', 'M', '930248342', '2');
INSERT INTO `xvi_finusuarios` (`id`, `apellidom`, `apellidop`, `direccion`, `dni`, `email`, `est_activo`, `fecha_nac`, `fecha_reg`, `nombre`, `password`, `sexo`, `telefono`, `pais_id`) VALUES (NULL, 'taype3', 'ruiz3', 'av brasil3', '47418893', 'wilhelm3@gmail.com', 1, '2019-05-03', '2019-05-23', 'wilhelm3', '$2a$10$3S4oAwBjXm2mh07Bj0MQ3uuk8ilmCQmuzbBaXekI4m6whrK91pdqa', 'M', '930248343', '3');
INSERT INTO `xvi_finusuarios` (`id`, `apellidom`, `apellidop`, `direccion`, `dni`, `email`, `est_activo`, `fecha_nac`, `fecha_reg`, `nombre`, `password`, `sexo`, `telefono`, `pais_id`) VALUES (NULL, 'taype4', 'ruiz4', 'av brasil4', '47418894', 'wilhelm4@gmail.com', 1, '2019-05-04', '2019-05-24', 'wilhelm4', '$2a$10$prLlMtn2eu6RzswaQpCgCeMHWKfil0m7JTmo/1gq1J/PUDIZziDbq', 'M', '930248344', '4');
INSERT INTO `xvi_finusuario_deportistas` (`id`, `altura`, `est_activo`,  `observacion`, `peso`, `deporte_id`, `usuario_id`) VALUES (NULL, '1.75', '1', 'asdasd', '54.5', '1', '1');
INSERT INTO `xvi_finusuario_deportistas` (`id`, `altura`, `est_activo`,  `observacion`, `peso`, `deporte_id`, `usuario_id`) VALUES (NULL, '1.75', '1', 'asdasd', '54.5', '1', '2');
INSERT INTO `xvi_finusuario_deportistas` (`id`, `altura`, `est_activo`,  `observacion`, `peso`, `deporte_id`, `usuario_id`) VALUES (NULL, '1.75', '1', 'asdasd', '54.5', '1', '3');
INSERT INTO `xvi_finusuario_deportistas` (`id`, `altura`, `est_activo`,  `observacion`, `peso`, `deporte_id`, `usuario_id`) VALUES (NULL, '1.75', '1', 'asdasd', '54.5', '1', '4');

INSERT INTO `xvi_finusuario_delegados` (`id`, `cargo`, `est_activo`, `deported_id`, `usuariodl_id`) VALUES (NULL, 'delegado de futbol', '1', '3', '2'), (NULL, 'delegado voley', '1', '1', '3');
INSERT INTO `xvi_finusuario_juez` (`id`, `descripcion`, `observaciones`, `deportej_id`, `usuarioj_id`) VALUES (NULL, 'juez imparcial', 'nada de nada', '2', '4'), (NULL, 'nda de nada ', 'juez parcal', '3', '3');
INSERT INTO `xvi_finequipos` (`id`, `d_decha_registro`, `nombre`, `equipodep_id`, `equipop_id`, `equipoudel_id`) VALUES (NULL, '2019-05-14', 'Delegacion  de Voley Peruano Equipo N° 1', '1', '2', '2');
INSERT INTO `xvi_finequipos` (`id`, `d_decha_registro`, `nombre`, `equipodep_id`, `equipop_id`, `equipoudel_id`) VALUES (NULL, '2019-05-14', 'Delegacion  de Voley Peruano Equipo N° 2', '1', '2', '2');

INSERT INTO `xvi_finequipo_deportistas` (`id`, `depequipoud_id`, `equipode_id`) VALUES (NULL, '1', '1');
INSERT INTO `xvi_finequipo_deportistas` (`id`, `depequipoud_id`, `equipode_id`) VALUES (NULL, '1', '1');
INSERT INTO `xvi_finequipo_deportistas` (`id`, `depequipoud_id`, `equipode_id`) VALUES (NULL, '2', '2');
INSERT INTO `xvi_finequipo_deportistas` (`id`, `depequipoud_id`, `equipode_id`) VALUES (NULL, '3', '2');
INSERT INTO `xvi_finequipo_deportistas` (`id`, `depequipoud_id`, `equipode_id`) VALUES (NULL, '4', '1');

INSERT INTO `xvi_finsponsor` (`id`, `est_activo`, `filosofia`, `logo_sponsor`, `nombre_completo`, `nombre_corto`, `nombre_mostrar`, `pag_web`) VALUES (NULL, '1', '\"SOMOS LOS MEJORES\"', 'IMAGEN1 LOGO .PNG', 'COCA COLA COMPANY SAC', 'COCA COLA', 'COCA COLA ES PANAMERICANOS', 'WWW.COCACOLA.COM');
INSERT INTO `xvi_finsponsor` (`id`, `est_activo`, `filosofia`, `logo_sponsor`, `nombre_completo`, `nombre_corto`, `nombre_mostrar`, `pag_web`) VALUES (NULL, '1', '\"SOMOS LOS MEJORES\"', 'IMAGEN2 LOGO .PNG', 'INCA COLA COMPANY SAC', 'INKA COLA', 'INKA COLA ES PANAMERICANOS', 'WWW.CINKACOLA.COM');
INSERT INTO `xvi_finsponsor` (`id`, `est_activo`, `filosofia`, `logo_sponsor`, `nombre_completo`, `nombre_corto`, `nombre_mostrar`, `pag_web`) VALUES (NULL, '1', '\"SOMOS LOS MEJORES\"', 'IMAGEN3 LOGO .PNG', 'PEPSI COLA COMPANY SAC', 'PEPSI', 'PESIS ES PANAMERICANOS', 'WWW.PEPSI.COM');
INSERT INTO `xvi_finsponsor` (`id`, `est_activo`, `filosofia`, `logo_sponsor`, `nombre_completo`, `nombre_corto`, `nombre_mostrar`, `pag_web`) VALUES (NULL, '1', '\"SOMOS LOS MEJORES\"', 'IMAGEN4 LOGO .PNG', 'SVEN COLA COMPANY SAC', '7UP', '7UP COLA ES PANAMERICANOS', 'WWW.7UP.COM');

INSERT INTO `xvi_finpublicidads` (`id`, `fecha_reg_publicidad`, `img_publicidad`, `video_publicidad`, `publicidads_id`) VALUES (NULL, '2019-05-06', 'imagen1.jpeg', 'www.youtube1.com', '1');
INSERT INTO `xvi_finpublicidads` (`id`, `fecha_reg_publicidad`, `img_publicidad`, `video_publicidad`, `publicidads_id`) VALUES (NULL, '2019-05-06', 'imagen2.jpeg', 'www.youtube2.com', '2');
INSERT INTO `xvi_finpublicidads` (`id`, `fecha_reg_publicidad`, `img_publicidad`, `video_publicidad`, `publicidads_id`) VALUES (NULL, '2019-05-06', 'imagen3.jpeg', 'www.youtube3.com', '3');


INSERT INTO `xvi_finroles` (`id`, `nombre`) VALUES (NULL, 'ROLE_ADMIN'), (NULL, 'ROLE_USER');
INSERT INTO `xvi_finuser_authorities` (`user_id`, `role_id`) VALUES ('1', '1'), ('2', '2'), ('3', '2'), ('4', '2');