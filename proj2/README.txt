Readme.txt

Ángel G. Romero Rosario
801-18-2064

Enlaces de los que obtuve información:

https://www.geeksforgeeks.org/message-dialogs-java-gui/
https://www.tutorialspoint.com/java/java_files_io.htm
https://docs.oracle.com/javase/7/docs/api/javax/swing/JList.html

I. Personas que me ayudaron:

    Jasiel Rivera me explicó cómo funcionaban los sets y cómo podría implementarlos.
    Ricardo Gonzalez me ayudó a implementar el juego en un package.

II. Personas a las que ayudé:

    Ayudé a Ricardo González a discernir una manera de implementar los turnos de los jugadores.
    Le expliqué a Jasiel como hacer output a un archivo externo.

III. Actualmente he podido implementar las siguientes cosas:

    - Puse el main en un .java aparte.
    - Puse todas las clases en un projecto llamado proj2.
    - La pila comienza con una carta y no vacio.
    - La pila es ahora su propia clase y es implementado usando la clase 
      MyStack que funciona como un ADT Stack.
    - La función de sort en la clase hand, organiza las cartas de acuerdo 
      al rank de la carta usando el algoritmo de Selection Sort.
    - El constructor de la clase Deck, se hizo de tal forma que se llenara 
      de cartas cada vez que se cree una instancia y luego se llama la funcion
      shuffle() para reorganizarlas aleatoriamente.
    - Implementé Hand como un DefaultListModel.
    - Cuando se añade una carta, invoca la funcion sort() y lo añade, asi se mantiene organizado el Hand.
    - Se añadió un botón que verifica si hay sets y los tira.
    - Puse un Action Listener que permite que cuando se le de a la 'x' para 
      cerrar la ventana del juego (GUI) se cerrara el programa y no siguiera corriendo 
      en el background.
    - La forma en la que se ponen los paneles lo cambie a un for loop.
    - Añadí un boton para los runs, pero no lo pude implementar.
    - Implementé una variable que permite escribir en un file externo el hand inicial 
      de los jugadores y luego va escribiendo que carta se esta "laying".
    - Cree una función que imita a un bot y juega como el segundo jugador. Es muy simple, 
      toma una carta del stack o del deck (aleatoriamente) y luego hace un lay.
    - Separé los if/else del action listener por jugador.
    - Si el Deck se vacia o un jugador se queda sin cartas evalua 
      las cartas e imprime un mensaje en forma de pop-up que indica
      qué jugador fue el que ganó y cierra el GUI.
    - Implementé los turnos de los jugadores utilizando 3 variables Booleanas.


IV. Design Patterns:

    - Hasta cierto punto, el programa implementa el Observer Design Pattern 
      en Table específicamente con los Hands.
    - Intenté implementar Singleton en Pile pero no era posible sin que alguna 
      de las clases se viera afectada negativamente.