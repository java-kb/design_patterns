In this example, the dialog box object is functioning as the mediator. Child widgets of the dialog box do not know, or care, who their siblings are. Whenever a simulated user interaction occurs in a child widget Widget::changed(), the widget does nothing except "delegate" that event to its parent dialog box mediator->widgetChanged(this).

FileSelectionDialog::widgetChanged() encapsulates all collective behavior for the dialog box (it serves as the hub of communication). The user may choose to "interact" with a simulated: filter edit field, directories list, files list, or selection edit field.

https://sourcemaking.com/design_patterns/mediator/cpp/1