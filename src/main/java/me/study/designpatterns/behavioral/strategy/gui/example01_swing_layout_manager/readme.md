Where can the Strategy Pattern be found in the Java class library? In the area of GUI pro-
gramming, you will fnd the Strategy Pattern in several places. For example, there are 
LayoutManagers or the “Look and Feel”; containers are provided with a certain strategy 
by default, which can, however, be exchanged by the user.
he FlowLayout is used for this. You can understand this, for example, by executing the 
following code once in the Java shell:
 javax.swing.JPanel pnlLayoutTest =
 new javax.swing.Jpanel();
 java.awt.LayoutManager layout =
 pnlLayoutTest.getLayout();
 System.out.println(layout);
The console will then output java.awt.
FlowLayout[hgap  5,vgap  5,align  center]. You can replace the 
strategy FlowLayout by passing a new strategy with pnlLayoutTest.
setLayout(layoutManager). The variable layoutManager must be of type 
LayoutManager.
The LayoutManger interface prescribes fve methods. The methods addLayout-
Component() and removeLayoutComponent() are needed if you want to address 
the components of the Jpanel with a string, otherwise the method bodies may remain 
empty. The preferredLayoutSize() method calculates and returns the optimal size 
of the Jpanel, while minimumLayoutSize() calculates and returns the minimum size 
of the Jpanel. The positioning of the components is done by the layoutContainer()
method. In this method, horizontal and vertical position as well as width and height are 
assigned to each component with setBounds().
In the source code for this book, you will fnd the example project LayoutStrategy, in 
which I implemented the LayoutManager – the Strategy – TwoColumnLayout. When 
you place components on the Jpanel, they are displayed in two columns. If you drag the 
GUI to the width, the components in the right column are enlarged accordingly. Figure 10.2
shows you what a GUI you create with TwoColumnLayout might look like.
