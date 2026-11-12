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


-- Datos iniciales para la tabla retos
-- Cada reto se inserta solo si su nombre no existe (evita duplicados al reiniciar)
-- y solo si ya hay al menos una empresa y una prioridad (id_empresa e id_prioridad son obligatorios).
INSERT INTO retos (id, nombre, descripcion, fecha_inicio, fecha_fin, estado, id_empresa, id_categoria, id_prioridad)
SELECT UUID(), 'Migración de Servidores a la Nube', 'Diseñar un plan para migrar la infraestructura local a AWS.', '2026-11-05', '2027-01-30', 'En progreso',
       (SELECT id_empresa FROM empresas LIMIT 1), (SELECT id FROM categorias WHERE nombre = 'Tecnología'), (SELECT id FROM prioridades LIMIT 1)
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM retos WHERE nombre = 'Migración de Servidores a la Nube')
  AND EXISTS (SELECT 1 FROM empresas)
  AND EXISTS (SELECT 1 FROM prioridades);

INSERT INTO retos (id, nombre, descripcion, fecha_inicio, fecha_fin, estado, id_empresa, id_categoria, id_prioridad)
SELECT UUID(), 'Campaña de Posicionamiento 2027', 'Crear una estrategia de marketing digital para redes sociales.', '2026-11-15', '2027-03-31', 'Pendiente',
       (SELECT id_empresa FROM empresas LIMIT 1), (SELECT id FROM categorias WHERE nombre = 'Marketing'), (SELECT id FROM prioridades LIMIT 1)
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM retos WHERE nombre = 'Campaña de Posicionamiento 2027')
  AND EXISTS (SELECT 1 FROM empresas)
  AND EXISTS (SELECT 1 FROM prioridades);