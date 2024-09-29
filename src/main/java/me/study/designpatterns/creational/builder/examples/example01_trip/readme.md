The most important parameters are probably the 
time period and the number of days you want to travel. It is also interesting to know how 
many people you will be traveling with and how many children (under 14) you will be 
taking with you. You will certainly also want to specify how many stars your accommoda-
tion should have. And important are the rating of the accommodation and the recommen-
dation rate. Maybe you want to be on the safe side and only want to see hotels that have 
already been rated by at least x guests. You save all these search parameters in the data 
felds of a travel object.

## Telescoping Constructor Pattern
Take a look at the Telescoping_Constructor_Pattern sample project. You pass the search 
parameters to the constructor.

```java
public fnal LocalDate startDate;
 public fnal LocalDate endDate;
 public fnal int duration;
 public fnal int numberTravellers;
 public fnal int numberKids;
 public fnal int minimumStars;
 public fnal int minimumRecommendations;
 public fnal int rating;
 public fnal int minimumNumberRatings;
 Trip(LocalDate startDate, LocalDate endDate,
 int duration, int numberTravellers,
 int numberKids, int minimumStars,
 int minimumRecommmendations,
 int rating, int minimumNumberRatings)
 {
 this.startDate = startDate;
 this.endDate = endDate;
 this.duration = duration;
 this.numberTravellers = numberTravellers;
 this.numberKids = numberKids;
 this.minimumStars = minimumStars;
 this.minimumRecommendations = minimumRecommendations;
 this.rating = rating;
 this.minimumNumberRatings = minimumNumberRatings;
 }
 }
```

Calling this constructor is sheer horror! The main method of the ApplStart class 
demonstrates this:

```java
 public static void main(String[] args) {
 var trip = new Trip(LocalDate.now(), LocalDate.now()
 , 7, 2, 3, 3, 80, 5, 30);
 System.out.println(travel);
 }
```

The problem is not only that you have to pass a lot of parameters – they are of the same 
type. You will never fnd out what each parameter stands for without documentation. 
Errors will creep in very reliably with such a call. One solution might be to overload the 
constructor. If the client doesn’t require a rating or even a minimum number of ratings, it 
should be allowed to omit them. The new constructor calls the one just presented with 
this() and passes default values:

Trip(LocalDate startDate, LocalDate enddate,
 int duration, int numberTravellers,
 int numberKids, int minimumStars,
 int minimumRecommendations) {
 this(startDate, enddate, duration, numberTravellers,
 numberKids, minimumStars,
 minimumRecommendations, 0, 0);
 }

The client now creates a travel object with a slightly shorter parameter list:
 public static void main(String[] args) {
 var trip = new Trip(LocalDate.now(), LocalDate.now(),
 7, 2, 3, 3, 80);
 System.out.println(travel);
 }

Is this call more appealing or clear? No! This solution, called the Telescoping 
Constructor Pattern, has made its way into the list of antipatterns. It’s never a good idea to 
have to pass too many parameters to a method – even more so when they are of the same 
type. Let’s look at another solution.

## JavaBeans Pattern
In Java introductions you will fnd the advice that data felds must be encapsulated and 
may only be read and modifed via getters and setters – and the version in the sample proj-
ect JavaBeans_Pattern is oriented to this. Information that cannot be dispensed with during 
construction, i.e. time frame, duration, and number of travelers, is stored in fnal data felds 
that are initialized by the constructor. The remaining information is preset with default 
values and can optionally be overwritten with setters.

 public class Travel {
 public fnal LocalDate startDate;
 public fnal LocalDate endDate;
 public fnal int duration;
 public fnal int numberTravellers;
 private int numberKids = 0;
 private int minimumStars = 0;
 private int minimumRecommendations = 0;
 private int rating = 0;
 private int minimumNumberRatings = 0;
 Trip(LocalDate startDate, LocalDate endDate,
 int duration, int numberTravellers) {
 this.startDate = startDate;
 this.endDate = endDate;
 this.duration = duration;
 this.numberTravellers = numberTravellers;
 }
 public void setNumberKids(int numberKids) {
 this.numberKids = numberKids;
 }
 // … abridged
 }

The client can now create an instance of the Trip class much more clearly.

 public static void main(String[] args) {
 var trip = new Trip(LocalDate.now(),
 LocalDate.now().plusDays(14), 14, 2);
 trip.setMinimumStars(3);
 trip.setMinimumRecommendations(80);
 System.out.println(trip);
 }

The code is indeed much more speaking now. However, the other data felds are not 
fnal and cannot be. Subsequent modifcation of the values is therefore allowed, even if it 
may not be desired. The approach is not quite the same as the JavaBeans specifcation, but 
merely adopts the essential principle. The JavaBeans specifcation requires that you always 
defne a default constructor. Incidentally, you will also occasionally come across opinions 
in the literature that relegate the JavaBeans specifcation to the realm of antipatterns – for 
the reasons just mentioned.
## Builder Pattern
Since the toolbox of OOP does not allow a satisfactory solution, it is time for a pattern. 
The Builder Pattern assumes an object whose task is limited to constructing another object. 
In the version in the Builder_Pattern sample project, you now realize the travel class by 
storing all information in fnal data felds. As with Singleton, the constructor is private, so 
that an object can only be created within the class. The constructor expects an object of 
type Builder as parameter.

public class Trip {
 // … abridged
 private fnal LocalDate startDate;
 private fnal LocalDate endDate;
 public fnal String start;
 public fnal String end;
 public fnal int duration;
 public fnal int numberTraveller;
 public fnal int numberKids;
 public fnal int minimumStars;
 public fnal int minimumRecommendations;
 public fnal int rating;
 public fnal int minimumNumberRatings;
 private Trip(Builder builder) {
 this.startDate = builder.startDate;
 this.endDate = builder.endDate;
 var formatter =
 DateTimeFormatter.ofPattern("MM/dd/YYYY");
 start = formatter.format(startDate);
 end = formatter.format(endDate);
 this.duration = builder.duration;
 this.numberTravellers = builder.numberTravellers;
 this.numberKids = builder.numberKids;
 this.minimumStars = builder.minimumStars;
 this.minimumRecommendations =
 builder.minimumRecommendations;
 this.rating = builder.rating;
 this.minimumNumberRatings =
 builder.minimumNumberRatings;
 }
 }

The Builder type is defned as the static inner class of the trip. The data felds of the 
trip are also declared here. The important data felds are initialized in the constructor, the 
others get default values. For each data feld, there is a setter that, contrary to language 
convention, only repeats the name of the feld. Inside the setter, the corresponding data 
feld is assigned a value; fnally, the setter returns the builder instance.

 public static class Builder {
 private LocalDate startDate;
 private LocalDate endDate;
 private int duration;
 private int numberTravellers;
 private int numberKids = 0;
private int minimumStars = 0;
 private int minimumRecommendations = 0;
 private int rating = 0;
 private int minimumNumberRatings = 0;
 public Builder(LocalDate startDate,
 LocalDate endDate, int duration,
 int numberTravellers) {
 this.startDate = startDate;
 // … abridged
 }
 public Builder numberKids(int numberKids) {
 this.numberKids = numberKids;
 return this;
 }
 public Builder minimumStars(int minimumStars) {
 this.minimumStars = minimumStars;
 return this;
 }
 // … abridged
 }

The build() method then fnally creates the journey object and passes the builder 
object to its constructor for this purpose.

 public journey build() {
 return new Trip(this);
 }

In the frst step, the client creates a builder, supplies it with the relevant data  – if 
required – and only then has a trip object returned.

 public static void main(String[] args) {
 var builder = new Trip.Builder(LocalDate.now(),
 LocalDate.now(), 15, 2);
 var trip = builder
 .minimumStars(3)
 .rating(5)
 .numberKids(0)
 .build();
 System.out.println(trip);
 }

This frst realization of the Builder Pattern should give you a sense that it can be useful 
to have objects whose functionality is limited to creating other objects. Please don’t be 
surprised that for simplicity reasons I have given LocalDate.now() for the startDate 
as well as for the endDate in the frst examples; in practice it is of course impracticable to 
have 15 days of vacation in the time "from today to today".
Somewhat unattractive is still the new Trip.builder in the main method. You can still 
create a builder in the trip class with a static method:

 public class Trip {
 // … abridged
 public static Trip.Builder builder(
 LocalDate startDate, LocalDate endDate,
 int duration, int numberTravellers) {
 return new Trip.Builder(startDate,
 endDate, duration, numberTravellers);
 }
 }
If you then want to do without a separate builder variable in the main method, the following code (which you will also fnd in the example) will also work
 public static void main(String[] args) {
 var trip = Trip.builder(LocalDate.now(),
 LocalDate.now().plusDays(15), 15, 2)
 .minimumStars(3)
 .rating(5)
 .numberKids(0)
 .build();
 System.out.println(trip);
 }
