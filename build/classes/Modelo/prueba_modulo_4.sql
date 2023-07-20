USE master;
DROP DATABASE RestauranteSuvi;
CREATE DATABASE RestauranteSuvi;
USE RestauranteSuvi;

CREATE TABLE Orden
(
    id          INT IDENTITY PRIMARY KEY,
    nombre      VARCHAR(100),
    cantidad    INT,
    precio      DECIMAL,
    cliente     VARCHAR(100),
    observacion VARCHAR(500)
);

