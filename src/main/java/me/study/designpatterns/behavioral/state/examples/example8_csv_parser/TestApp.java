package me.study.designpatterns.behavioral.state.examples.example8_csv_parser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        CsvParser csvParser = new CsvParser();
        ArrayList<String> fieldList = new ArrayList<>(List.of("first name", "email", "age"));
        try {
            csvParser.extractFields("user,user@example.com,30", fieldList);
            for (String field : fieldList) {
                System.out.print(field);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

class CsvParser {
    private final CsvParserState fieldStartState = new CsvParserFieldStartState(this);
    private final CsvParserState scanFieldState = new CsvParserScanFieldState(this);
    private final CsvParserState startQuotedState = new CsvParserStartQuotedState(this);
    private final CsvParserState endQuotedState = new CsvParserEndQuotedState(this);
    private final CsvParserState gotErrorState = new CsvParserGotErrorState(this);
    
    private CsvParserState currentState = fieldStartState;
    
    private String currentField;
    
    private ArrayList<String> fieldList = new ArrayList<>();

    public ArrayList<String> getFieldList() {
        return fieldList;
    }

    CsvParserState getState() {
        return currentState;
    }

    void setState(CsvParserState state) {
        this.currentState = state;
    }

    void setFieldStartState() {
        currentState = fieldStartState;
    }

    void setScanFieldState() {
        currentState = scanFieldState;
    }

    void setScanQuotedState() {
        currentState = startQuotedState;
    }

    void setEndQuotedState() {
        currentState = endQuotedState;
    }

    void setGotErrorState() {
        currentState = gotErrorState;
    }

    void AddCharToCurrField(Character c) {
        currentField += c;
    }

    void AddCurrFieldToList() {
        fieldList.add(currentField);
        currentField="";
    }

    void extractFields(String s, List fields) throws Exception {
        fieldList = (ArrayList) fields;
        fieldList.clear();
        
        currentField = "";
        for (int i = 0; i < s.length(); i++) {
            currentState.ProcessChar(s.charAt(i), i);
        }
        if (currentState instanceof CsvParserStartQuotedState || currentState instanceof CsvParserGotErrorState) {
            throw new Exception("Missing closing quote");
        }
        if (!currentField.isEmpty()) {
            AddCurrFieldToList();
        }
    }
}

abstract class CsvParserState {
    protected CsvParser csvParser;

    public CsvParserState(CsvParser csvParser) {
        this.csvParser = csvParser;
    }

    void ChangeState(CsvParserState state) {
        csvParser.setState(state);
    }

    void AddCharToCurrField(Character c) {
        csvParser.AddCharToCurrField(c);
    }

    void AddCurrFieldToList() {
        csvParser.AddCurrFieldToList();
    }

    abstract void ProcessChar(Character c, int position) throws Exception;
}

class CsvParserFieldStartState extends CsvParserState {

    public CsvParserFieldStartState(CsvParser csvParser) {
        super(csvParser);
    }

    @Override
    void ProcessChar(Character c, int position) {
        switch (c) {
            case '"':
                csvParser.setScanQuotedState();
                break;
            case ',':
                AddCurrFieldToList();
                break;
            default:
                AddCharToCurrField(c);
                csvParser.setScanFieldState();
                break;
        }
    }

}

class CsvParserScanFieldState extends CsvParserState {

    public CsvParserScanFieldState(CsvParser csvParser) {
        super(csvParser);
    }

    @Override
    void ProcessChar(Character c, int position) {
        if (c.equals(',')) {
            AddCurrFieldToList();
            csvParser.setFieldStartState();
        } else {
            AddCharToCurrField(c);
        }
    }

}

class CsvParserStartQuotedState extends CsvParserState {

    public CsvParserStartQuotedState(CsvParser csvParser) {
        super(csvParser);
    }

    @Override
    void ProcessChar(Character c, int position) {
        if (c.equals('"')) {
            csvParser.setEndQuotedState();
        } else {
            AddCharToCurrField(c);
        }
    }

}

class CsvParserEndQuotedState extends CsvParserState {

    public CsvParserEndQuotedState(CsvParser csvParser) {
        super(csvParser);
    }

    @Override
    void ProcessChar(Character c, int position) {
        if (c.equals(',')) {
            AddCurrFieldToList();
            csvParser.setFieldStartState();
        } else {
            csvParser.setGotErrorState();
        }
    }
}

class CsvParserGotErrorState extends CsvParserState {

    public CsvParserGotErrorState(CsvParser csvParser) {
        super(csvParser);
    }

    @Override
    void ProcessChar(Character c, int position) throws Exception {

        throw new Exception(String.format("Error in line at position %d", position));
    }

}