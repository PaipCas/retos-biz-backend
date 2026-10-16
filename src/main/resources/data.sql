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
