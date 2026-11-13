-- Datos iniciales para la tabla categorias
-- INSERT IGNORE evita errores al reiniciar la app: si el nombre ya existe (columna unique), la fila se omite.

INSERT IGNORE INTO categorias (id, nombre, descripcion, area_responsable) VALUES
(UUID(), 'Tecnología', 'Retos relacionados con desarrollo de software, infraestructura y transformación digital', 'Sistemas'),
(UUID(), 'Innovación', 'Retos para proponer nuevos productos, servicios o modelos de negocio', 'Dirección de Innovación'),
(UUID(), 'Sostenibilidad', 'Retos enfocados en reducir el impacto ambiental y promover prácticas responsables', 'Gestión Ambiental'),
(UUID(), 'Marketing', 'Retos de posicionamiento de marca, publicidad y estrategias digitales', 'Mercadeo'),
(UUID(), 'Finanzas', 'Retos para optimizar costos, presupuestos y procesos financieros', 'Financiera'),
(UUID(), 'Talento Humano', 'Retos sobre bienestar laboral, capacitación y cultura organizacional', 'Recursos Humanos'),
(UUID(), 'Logística', 'Retos para mejorar la cadena de suministro, inventarios y distribución', 'Operaciones'),
(UUID(), 'Servicio al Cliente', 'Retos para mejorar la atención, satisfacción y fidelización de clientes', 'Atención al Cliente');


-- Datos iniciales para la tabla empresas
-- INSERT IGNORE evita errores al reiniciar la app: si el NIT ya existe (columna unique), la fila se omite.

INSERT IGNORE INTO empresas (id_empresa, nombre_empresa, nit_empresa, direccion_empresa, telefono_empresa, sector_empresa) VALUES
(UUID(), 'Soluciones Digitales S.A.S.', '900123456-1', 'Calle 10 # 20-30, Medellín', '3001234567', 'Tecnología'),
(UUID(), 'EcoVerde Ltda.', '900234567-2', 'Carrera 43A # 5-15, Medellín', '3012345678', 'Sostenibilidad'),
(UUID(), 'Mercadeo Creativo S.A.S.', '900345678-3', 'Avenida El Poblado # 12-45, Medellín', '3023456789', 'Marketing'),
(UUID(), 'Finanzas del Valle S.A.', '900456789-4', 'Calle 50 # 45-10, Medellín', '3034567890', 'Finanzas'),
(UUID(), 'Logística Antioquia S.A.S.', '900567890-5', 'Carrera 65 # 30-20, Medellín', '3045678901', 'Logística');