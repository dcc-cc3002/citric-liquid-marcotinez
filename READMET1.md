# Readme Tarea 1.

## ¿Qué funcionalidades implementó?

Se implementaron las clases correspondientes a las unidades (Character y WildUnits), junto con
sus traits y clases abstractas.

En playerCharacter se añaden los siguientes métodos: normaCount, normaClear, starsCount, starsBonus
y starDrop (las variables sobre las que trabajan los setters y getter implementados en 
este punto aún no tienen su privacidad definida).

Por otro lado, se crearon las clases de cada uno de los Wild Unit, cada uno se inicializa con sus
estadísticas de enunciado, además se deja trait en caso de ser necesario añadir más métodos.

Para el caso de los paneles se añade el método capaz de quitar y añadir paneles (gran cambio con
respecto a la entrega parcial 1) y unos getters para characters y nextPanels.

## ¿Por qué tomó ciertas decisiones en su diseño?

Uno de los cambios más grandes fue quitar los nextPanels como argumentos de los constructores, esto
debido a los métodos que permiten añadir y quitar paneles.

Se decidió dejar los traits de los Wild Units para poder añadir métodos en caso de ser necesario.

Se decidió dejar todos los metódos encargados de cambiar variables del player dentro de PlayerCharacter,
la idea es que estos sean llamados desde cada panel correspondiente.

## ¿Cómo está organizado su código?

El código se organizó en dos carpetas principales, entities que encapsula al character y WildUnits, y
por otro lado, Panels.

