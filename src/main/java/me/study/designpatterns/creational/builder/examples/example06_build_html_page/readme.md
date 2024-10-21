
*Source: https://sourcemaking.com/design_patterns/builder/php/1*

In the Builder Pattern a director and a builder work together to build an object. The director controls the building and specifies what parts and variations will go into an object. The builder knows how to assemble the object given specification.

In this example we have a director, HTMLPageDirector, which is given a builder, HTMLPageBuilder. The director tells the builder what the pageTitle will be, what the pageHeading will be, and gives multiple lines of text for the page. The director then has the builder do a final assembly of the parts, and return the page.

<?php

abstract class AbstractPageBuilder {
    abstract function getPage();
}

abstract class AbstractPageDirector {
    abstract function __construct(AbstractPageBuilder $builder_in);
    abstract function buildPage();
    abstract function getPage();
}

class HTMLPage {
    private $page = NULL;
    private $page_title = NULL;
    private $page_heading = NULL;
    private $page_text = NULL;
    function __construct() {
    }
    function showPage() {
      return $this->page;
    }
    function setTitle($title_in) {
      $this->page_title = $title_in;
    }
    function setHeading($heading_in) {
      $this->page_heading = $heading_in;
    }
    function setText($text_in) {
      $this->page_text .= $text_in;
    }
    function formatPage() {
       $this->page  = '<html>';
       $this->page .= '<head><title>'.$this->page_title.'</title></head>';
       $this->page .= '<body>';
       $this->page .= '<h1>'.$this->page_heading.'</h1>';
       $this->page .= $this->page_text;
       $this->page .= '</body>';
       $this->page .= '</html>';
    }
}

class HTMLPageBuilder extends AbstractPageBuilder {
    private $page = NULL;
    function __construct() {
      $this->page = new HTMLPage();
    }
    function setTitle($title_in) {
      $this->page->setTitle($title_in);
    }
    function setHeading($heading_in) {
      $this->page->setHeading($heading_in);
    }
    function setText($text_in) {
      $this->page->setText($text_in);
    }
    function formatPage() {
      $this->page->formatPage();
    }
    function getPage() {
      return $this->page;
    }
}

class HTMLPageDirector extends AbstractPageDirector {
    private $builder = NULL;
    public function __construct(AbstractPageBuilder $builder_in) {
         $this->builder = $builder_in;
    }
    public function buildPage() {
      $this->builder->setTitle('Testing the HTMLPage');
      $this->builder->setHeading('Testing the HTMLPage');
      $this->builder->setText('Testing, testing, testing!');
      $this->builder->setText('Testing, testing, testing, or!');
      $this->builder->setText('Testing, testing, testing, more!');
      $this->builder->formatPage();
    }
    public function getPage() {
      return $this->builder->getPage();
    }
}

  writeln('BEGIN TESTING BUILDER PATTERN');
  writeln('');

  $pageBuilder = new HTMLPageBuilder();
  $pageDirector = new HTMLPageDirector($pageBuilder);
  $pageDirector->buildPage();
  $page = $pageDirector->getPage();
  writeln($page->showPage());
  writeln('');
 
  writeln('END TESTING BUILDER PATTERN');

  function writeln($line_in) {
    echo $line_in."<br/>";
  }

?>
Output
BEGIN TESTING BUILDER PATTERN

<html>
<head><title>Testing the HTMLPage</title></head>
<body>
<h1>Testing the HTMLPage</h1>
Testing, testing, testing!
Testing, testing, testing, or!
Testing, testing, testing, more!
</body>
</html>

END TESTING BUILDER PATTERN