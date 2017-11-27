# A3 Artatawe implementation.

## How to install it from Github
```
git clone http://github.com/hadalhw17/Artatawe.git
```
## Environment Setup

### Intellij

1. Open intellij.
2. "File" >> "New" >> "Project From Existing Sources".
3. Select the location of local repository.
4. Tick "Import From External Module" and select Maven.
6. Build project.

### Eclipse

1. Open eclipse
2. Click "File" >> "Import Projects from FileSystem".
3. Set the "Import Source" directory to the location of the local repository.
4. Press finish and wait.
5. Build project.


## How to create new GUI page

### 1.All of GUI pages are subclasses of **ScenePattern.java**.

All main layout elements(Header,Side menu) are composed here. You don't need to override it.
Methods not to override:
* ```public BorderPane getPane()```
* ```public void setContentPane()```
* ```public VBox addDrawerVBox()```
* ``` public HBox addHBox()```
* ```public void setNameLabel(String nameOfPage)```
* ```public void menuActivity()```

Methods to override:
* ```public JFXMasonryPane constructContentPane()```


### 2.Method **public JFXMasonryPane constructContentPane()** is responisble for generating dynamic content.

**JFXMasonryPane** is an object in JFoenix libriary. It is a panel where content location is dynamic and changes according to:
* Screen size
* Object size
It tries to place objects in a way to fit all space between objects.
All of the objects that are needed to be added to this pane should be located in separate panels, so if I want to add an image box and a text field to the main panel, I should first split them onto two differnt panels:
```
Pane pane;
Pane pane1;
pane = new Pane(new ImageView('image')); //Add image to panel
pane1 = new Pane(new JFXButton("I am a button");//Add button to panel

contentPane.getChildren().addAll(pane, pane1);//Add both of these panels to main panel

```
### 3.Button actions.

To add an action to a button you need a lambda expression below.

```
JFXButton btn = new JFXButton();
btn.setOnMousePressed(e -> {
    //All actions go here
});

```
### 4.Try to use elements provided by JFoeniX instead of JavaFX.

Almost all JavaFX elements have JFoeniX analog.
It just starts with **JFX**, so instead of a normal **Button** there will be **JFXButton**.

### 5.Example of **public JFXMasonryPane constructContentPane()** method used to construct custom avatar page.

```
 public JFXMasonryPane constructContentPane(){
        JFXMasonryPane root = new JFXMasonryPane();
        JFXColorPicker colorPicker = new JFXColorPicker();
        JFXSlider sizeSlider = new JFXSlider();
        sizeSlider.setMinWidth(200);
        sizeSlider.setMinHeight(10);
        sizeSlider.setOrientation(Orientation.HORIZONTAL);
        Pane pane1 = new Pane();
        Pane pane = new Pane();
        VBox pane2 = new VBox();
        Canvas canvas = new Canvas(1000, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D( );
        canvas.setOnMouseDragged(e->{
            double size = sizeSlider.getValue();
            double x = e.getX() - size/2;
            double y = e.getY() - size-2;
            gc.setFill(colorPicker.getValue());
            gc.fillRect(x,y,size,size);
        });
        pane.getChildren().add(canvas);
        pane1.getChildren().add(colorPicker);
        pane2.getChildren().addAll(sizeSlider, new Label("Resize brush"));
        root.getChildren().addAll(pane,pane1,pane2);
        return root;
    }
    
```
### 6.Pages needed to be created.
* Page where all of the profiles are going to be displayed
* Page where all of the auctions needed to be added

If anyone wants to do one of that pages, just write your name above.
