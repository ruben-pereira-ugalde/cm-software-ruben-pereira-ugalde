# cm-software-ruben-pereira-ugalde

Gilded Rose Refactoring

Cambios por clase

GildedRose

Se implementó Singleton (getInstance) para asegurar una única instancia.

Se eliminó la lógica de actualización embebida; se delega en clases ItemUpdater.

updateQuality() simplificado: itera la lista y delega la actualización.

Agregado JavaDoc para describir responsabilidades.

Item

Campos pasaron a private; acceso controlado por getters y setters.

Se crearon métodos claros: decrementSellIn(), increaseQuality(), decreaseQuality(), resetQuality().

setQuality(int) valida que la calidad quede entre 0 y 50.

Eliminado acceso público directo a atributos y lógica de presentación separada.

ItemUpdaterFactory

Introducida una factoría que asocia nombres de ítems a estrategias ItemUpdater.

Uso de Map<String, ItemUpdater> (registry) para registro dinámico.

Facilita cumplir el principio Open/Closed: agregar nuevos tipos sin modificar código existente.

ItemUpdater (interfaz)

Define el contrato update(Item) para todas las estrategias.

Permite polimorfismo y desacoplamiento entre GildedRose y las implementaciones.

DefaultItemUpdater

Lógica de actualización para ítems comunes.

Utiliza métodos de Item para alterar quality y sellIn.

Evita duplicación de código mediante reutilización de decreaseQuality().

AgedBrieUpdater

Lógica específica para Aged Brie: incrementa calidad antes y después de vencimiento.

Reutiliza increaseQuality() de Item.

BackstagePassesUpdater

Lógica de actualización para Backstage passes:

Incremento adicional si sellIn ≤ 10 y ≤ 5.

Resetea quality a 0 tras fecha de concierto (sellIn < 0).

SulfurasUpdater

Estrategia para Sulfuras (legendario): método update vacío (sin cambios a sellIn ni quality).

Principios aplicados

DRY: sin duplicación de lógica.

KISS: lógica simple y directa.

SOLID: Single Responsibility, Open/Closed, Liskov, Interface Segregation, Dependency Inversion.

Alta cohesión y bajo acoplamiento.

Modularidad y extensibilidad: fácil agregar nuevos comportamientos.

Reutilización de componentes y métodos comunes.
