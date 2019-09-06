# before

> Expected :[Article(title=Can detect title diff, writer=Writer(name=takahirom), tags=[Tag(name=Kotlin), Tag(name=Multiplatform), Tag(name=Android)], dateTime=DateTime(date=Date(month=10, dayOfMonth=23), time=Time(hour=9, minute=0))), Article(title=Can detect list size diff, writer=Writer(name=takahirom), tags=[Tag(name=Kotlin), Tag(name=Multiplatform), Tag(name=Android)], dateTime=DateTime(date=Date(month=10, dayOfMonth=23), time=Time(hour=9, minute=0))), Article(title=Can detect nested class diff, writer=Writer(name=takahirom), tags=[Tag(name=Kotlin), Tag(name=Multiplatform), Tag(name=Android)], dateTime=DateTime(date=Date(month=10, dayOfMonth=23), time=Time(hour=9, minute=0)))]  
Actual   :[Article(title=Can detect title change?, writer=Writer(name=takahirom), tags=[Tag(name=Kotlin), Tag(name=Multiplatform), Tag(name=Android)], dateTime=DateTime(date=Date(month=10, dayOfMonth=23), time=Time(hour=9, minute=0))), Article(title=Can detect list size diff, writer=Writer(name=takahirom), tags=[Tag(name=Kotlin), Tag(name=Multiplatform), Tag(name=Android), Tag(name=Test)], dateTime=DateTime(date=Date(month=10, dayOfMonth=23), time=Time(hour=9, minute=0))), Article(title=Can detect nested class diff, writer=Writer(name=takahirom), tags=[Tag(name=Kotlin), Tag(name=Multiplatform), Tag(name=Android)], dateTime=DateTime(date=Date(month=10, dayOfMonth=23), time=Time(hour=9, minute=1)))]

# after

```
java.lang.AssertionError: 
target[0].title Expected <Can detect title diff>, actual <Can detect title change?> is not same.
target[1].tags Expected size <3>, actual size <4> is not same.
target[2].dateTime.time.minute Expected <0>, actual <1> is not same.
```