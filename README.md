# wheel4j

Classes like 'reinventing the wheel'.

## Case
The Case class extends the switch statement.
```$java
int val = 2;
String text = Case.of(val)
                .when(1).then("one")
                .when(2).then("two")
                .then(3).then("thee")
                .other().then("other")
                .end()
```

Expressions can be used as conditions.
```$java
int val = 2;
String text = Case.of(val)
                .when(0).then("Zero")
                .when(v -> v % 2 == 0).then("Even")
                .when(v -> v % 2 == 1).then("Odd")
                .end()
```

Like the switch statement, you can execute the expression when it matches the condition.
```$java
String val = "hello wheel4j";
int length = Case.of(val)
                .whenNull().then(0)
                .when(v -> v.isEmpty()).then(0)
                .other().then(v -> v.length())
                .end()
```

You can call method when condition match.
```$java
String val = "hello wheel4j";
Case.of(val)
        .whenNull().call(v -> System.out.println("Null"))
        .when(v -> v.isEmpty()).call(System.out.println("Empty"))
        .other().call(v -> System.out.println("Length:" + v.length()))
        .end()
```

## Evaluations
`equals` method evaluate two objects.

It's compare internal states even for class that do no have `equals` method.

```$java
class SuperClass {
    private final int value1;

    SuperClass(int value1) {
        this.value1 = value1;
    }
    
}

class SubClass extends SuperClass {
    private final Long value2;

    SubClass(int value1, Long value2) {
        super(value1);
        this.value2 = value2;
    }
}
String value1 = "Hello wheel4j";
String value2 = "Hello " + "wheel4j";
if (Evaluations.equals(value1, value2)) { // result is true
    // ...
}
```