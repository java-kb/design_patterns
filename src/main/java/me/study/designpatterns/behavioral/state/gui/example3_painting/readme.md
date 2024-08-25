Most programs provide toolbars with different tools. For example Zoom-Buttons, Paint-Buttons (drawing of circles, rectangles,.. ). When the Zoom-Button is pressed, the user can zoom in or out, when the Paint-Button is pressed, the user can paint s.th : each tool changes the behavior of the program. You can realize this through different flags:
```delphi
procedure TForm.FormMouseDown/FormMouseMove/FormMouseUp 
begin 
  //.. 
  if fZoomFlag then 
  begin 
    //... 
  end else 
  if fPaintFlag then 
  begin 
    //... 
  end; 
  //.. 
end; 
```
Even if we want to create a simple program like MSPaint, we will need a lot of flags: fPenFlag, fDrawRectFlag, fDrawCircleFlag, fEraserFlag,... This results in 'elephant'-procedures and a general view becomes difficult.

You can avoid such 'elephant'-procedures through the state design pattern. GoF define the State design pattern in this way: it allows an object to alter its behavior when its internal state changes. The object will appear to change its class.

In our example, the behavior for each tool is encapsulated in a different 
```delphi
class:

tTool = class 
private 
  fForm : tForm; 
public 
  constructor Create(Form : TForm); 
  destructor Destroy; override; 
  procedure SetCursor; virtual; 
  procedure HandleMouseDown(x,y : integer); virtual; 
  procedure HandleMouseMove(x,y : integer); virtual; 
  procedure HandleMouseUp(x,y : integer); virtual; 
  procedure StopAction; virtual; 
end; 

tZoomTool = class(tTool) 
public 
  procedure SetCursor; override; 
  procedure HandleMouseDown(x,y : integer); override; 
  procedure HandleMouseMove(x,y : integer); override; 
  procedure HandleMouseUp(x,y : integer); override; 
end; 

tPaintTool = class(tTool) 
public 
  procedure SetCursor; override; 
  procedure HandleMouseDown(x,y : integer); override; 
  procedure HandleMouseMove(x,y : integer); override; 
  procedure HandleMouseUp(x,y : integer); override; 
end;
Now we can replace all the flags by a simple parameter fTool of class tTool. Instead of setting different flags, an object of a derived class is created:

procedure TForm.SetZoomModus; 
begin 
  fTool.Destroy; 
  fTool := tZoomTool.Create(Self); 
end; 

procedure TForm.SetPaintModus; 
begin 
  fTool.Destroy; 
  fTool := tPaintTool.Create(Self); 
end; 
So that in the MouseDown/MouseMove/MouseUp events the appropriate method is called:

procedure TForm.FormMouseDown({...} X, Y: Integer); 
begin 
  fTool.HandleMouseDown(x,y); 
end; 

procedure TForm.FormMouseMove({...} X, Y: Integer); 
begin 
  fTool.HandleMouseMove(x,y); 
end; 

procedure TForm.FormMouseUp({...} X, Y: Integer); 
begin 
  fTool.HandleMouseUp(x,y); 
end;
```