CREATE DATABASE CLIENTES
GO
USE CLIENTES
GO
CREATE TABLE CLIENTES(
		IDCLIENTE INT PRIMARY KEY,
		NOMBRE VARCHAR(50),
		ESTADO VARCHAR(50),
		CREDITO MONEY,
		DEUDA MONEY
)
select * from clientes where 

INSERT INTO CLIENTES (IDCLIENTE, NOMBRE, ESTADO, CREDITO, DEUDA) VALUES 
(1, 'Juan Perez', 'Baja California', 50000.00, 12000.00),
(2, 'Maria Lopez', 'Sonora', 75000.00, 15000.00),
(3, 'Carlos Ramirez', 'Chihuahua', 60000.00, 10000.00),
(4, 'Ana Martinez', 'Coahuila', 45000.00, 8000.00),
(5, 'Luis Hernandez', 'Nuevo León', 90000.00, 22000.00),
(6, 'Rosa Garcia', 'Tamaulipas', 55000.00, 17000.00),
(7, 'Jorge Sanchez', 'Durango', 30000.00, 5000.00),
(8, 'Martha Ruiz', 'Zacatecas', 70000.00, 20000.00),
(9, 'Miguel Flores', 'Sinaloa', 65000.00, 18000.00),
(10, 'Elena Diaz', 'Sonora', 80000.00, 24000.00),
(11, 'Ricardo Gomez', 'Chihuahua', 72000.00, 13000.00),
(12, 'Laura Salinas', 'Baja California Sur', 40000.00, 6000.00),
(13, 'Enrique Torres', 'Nuevo León', 82000.00, 21000.00),
(14, 'Sofia Navarro', 'Coahuila', 38000.00, 9000.00),
(15, 'Oscar Morales', 'Tamaulipas', 50000.00, 12000.00),
(16, 'Gloria Rivas', 'Durango', 67000.00, 19000.00),
(17, 'David Espinoza', 'Sinaloa', 77000.00, 25000.00),
(18, 'Patricia Vega', 'Baja California', 60000.00, 11000.00),
(19, 'Andres Mendoza', 'Sonora', 45000.00, 8000.00),
(20, 'Paula Ortiz', 'Zacatecas', 90000.00, 30000.00);
