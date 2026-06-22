# 99.7% Citric Liquid

## About

`99.7% Citric Liquid` is a simplified clone of the renowned game, `100% Orange Juice`. Its main
purpose is to serve as an educational tool, teaching foundational programming concepts.

📢 **Note**: This project is purely educational and will not be used for any commercial purposes.

---

## For Students

The remainder of this README is yours to complete. Take this opportunity to describe your
contributions, the design decisions you've made, and any other information you deem necessary.

---
En esta entrega se implementaron distintas funcionalidades vistas en clases, como por ejemplo:
- Se utilizó Double Dispatch para la entrega de estrellas y victorias, dependiendo del tipo del Character.
- La elección del objetivo para subir el nivel de la norma se hizo mediante un patrón de diseño de la clase 12 
- y está basada en los estados posibles.
- Se implementó nuevamente normaCheck y normaClear, estas fueron renovadas para que funcionen con las nuevas normas.
- Se fixeo el getter de los ArrayBuffer, ahora deberían retornar una copia no mutable correctamente.

Decisiones de diseño:
- Como aún no se utilizan inputs, la evasión y defensa de WildUnits y PlayerCharacter es aleatoria.
- Se crearon clases independientes para cada norma, en ellas están los atributos para subir de nivel y la norma siguiente.
- Se separan las clases abstractas en 3, una en comun para WildUnit y PlayerCharacter, otra para WildUnit y otra para PlayerCharacter,
en estas dos ultimas se implementó el double dispatch para la entrega de estrellas y victorias.
- El modo recovery no se implementó aún, ya que no aparecía en la entrega, de todas formas existe la variable booleana "enCombate",
que se utiliza para saber si la unidad está disponible para el combate o no (muerta :$).
- El neutral Panel quedó con el metodo apply vacio.
- Al momento de entrega tengo un 99% de coverage en la carpeta model.


<div style="text-align:center;">
    <img src="https://i.creativecommons.org/l/by/4.0/88x31.png" alt="Creative Commons License">
</div>

This project is licensed under the [Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/).

---