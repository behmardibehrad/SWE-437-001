# Maintainability
###### Pisith, Behrad, Mathias
###### SWE437 - Homework 2

```
In order to future proof our code, we would need to make sure that our code 
is broken down into different reusable components as much as possible.
Thanks to the original programmers that made everything easy for us to
 reuse the existing classes to work with our new written code.
For example, we create a new class call SWE437HW2. This class contain
related information, menu, and functions, enough to re-create similar functionality in the web app.
We designed this class so that we can make various calls to the existing classes 
such as QuoteList, Quotes.xml, and Quote, which in turn obtain the same functionality as the web app. 
Next time, even if we need to write a mobile or desktop GUI app, 
we should be able to apply this reusable strategy to create the 
specific code for specific application. 
```
```
While the original programmers did an amazing job designing the classes for reusability,
which made it easy for us to extend it to a command line interface program,
there are some refactoring that we could do to improve the program for better 
maintainability. For example, in quoteserve.java, the original programmers specify 
a few static variables that happen to be a link URLS to specific pages of the website. 
Those links are FileURL, quoteFileName, and this Servlet. The problem is when the URL changes, 
for example from cs.gmu.edu to something else, then the links will no longer work. We could 
easily make changes to the .java file and redeploy the app, but that means that we 
would have to do this every single time. The preferred way would be dynamically replace 
the host name as the application changes and grow. We also need to decompose our methods 
as much as we can, so that we can reuse them at a later time. For example, 
method userSearchAnswer() and  printMenu().

In order to future proof our code, we would need to make sure that our code is broken down into different reusable components as much as possible.
Thanks to the original programmers that made everything easy for us to reuse the existing classes to work with our new written code.
For example, we create a new class call SWE437HW2.java. This class contain related information, menu, and functions, enough to re-create similar functionality of the web application into a command line interface.
We designed this class so that we can make various calls to the existing classes such as QuoteList, Quotes.xml, and Quote, which in turn obtain the same functionality as the web app. 
Next time, even if we need to write a mobile or desktop GUI app, we should be able to apply this reusable strategy to create the specific code for specific application. 

```

