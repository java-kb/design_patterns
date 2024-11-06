# Exporting shapes into XML
*Source: https://refactoring.guru/design-patterns/visitor/java/example*

In this example, we would want to export a set of geometric shapes into XML. The catch is that we don’t want to change the code of shapes directly or at least keep it to the minimum.

In the end, the Visitor pattern establishes an infrastructure that allows us to add any behaviors to the shapes hierarchy without changing the existing code of those classes.

 OutputDemo.txt: Execution result

    <?xml version="1.0" encoding="utf-8"?>
    <circle>
        <id>2</id>
        <x>23</x>
        <y>15</y>
        <radius>10</radius>
    </circle>

<?xml version="1.0" encoding="utf-8"?>

    <compound_graphic>
    <id>4</id>
        <dot>
            <id>1</id>
            <x>10</x>
            <y>55</y>
        </dot>
        <circle>
            <id>2</id>
            <x>23</x>
            <y>15</y>
            <radius>10</radius>
        </circle>
        <rectangle>
            <id>3</id>
            <x>10</x>
            <y>17</y>
            <width>20</width>
            <height>30</height>
        </rectangle>
        <compound_graphic>
        <id>5</id>
            <dot>
                <id>1</id>
                <x>10</x>
                <y>55</y>
            </dot>
        </compound_graphic>
    </compound_graphic>