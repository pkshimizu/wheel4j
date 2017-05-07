# wheel4j

Classes like 'reinventing the wheel'.

## Case
The Case class extends the switch statement.
```$java
int val = 2;
String text = Case.of(val)
                .when(1, "one")
                .when(2, "two")
                .then(3, "thee")
                .other("other")
                .end()
```

Expressions can be used as conditions.
```$java
int val = 2;
String text = Case.of(val)
                .when(0, "Zero")
                .when(v -> v % 2 == 0, "Even")
                .when(v -> v % 2 == 1, "Odd")
                .end()
```

Like the switch statement, you can execute the expression when it matches the condition.
```$java
String val = "hello wheel4j";
int length = Case.of(val)
                .when(null, 0)
                .when(v -> v.isEmpty(), 0)
                .other(v -> v.length())
                .end()
```
