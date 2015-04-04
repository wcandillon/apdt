# Introduction #

Every Aspect have to be a subtype of the Aspect abstract class. This class gives you the aspect reflection capabilities. The anatomy of an Aspect is a class with extra annotations. Pointcut and advice declarations can be made using the @Before, @After and @Around annotations. For instance, @After(call(Order->addItem)) intercept all calls to the addItem method in an instance of Order.

During the program execution, aspects are first classes entities. aspect's life cycle is control of the _AspectRegistry_ class. In futur version, users will have more control an aspect's life cycle and an aspect will have the capability to be persistent in session.

# Joinpoints #

The current implementation of phpAspect counts 3 kinded joinpoints:
  * Method execution
  * Method call
  * Object construction

There is also 2 non-kinded joinpoints:
  * File
  * Within

Kinded joinpoints match a specific type of joinpoint using a signature and non-kinded joinpoints match all type of joinpoints using variety of properties.

## Method execution ##

The method execution joinpoint identifies certains method executions in the program call graph. The signature of this joinpoint is the following:
```
/**
 * @Before(exec(Foo::bar))
 */
public function beforeFooBar(Joinpoint $jp){]
```

## Method call ##

The method execution joinpoint identifies certains method calls in the program call graph. The difference with the method execution joinpoint is the context: this joinpoint access the calling context and doesn't see the execution context. The signature of this joinpoint is the following:
```
/**
 * @Before(call(Foo->bar))
 */
public function beforeFooBar(Joinpoint $jp){]
```

## Constructor ##

The construction joinpoint identifies object constructions the program call graph. The signature of this joinpoint is the following:
```
/**
 * @Before(new(Foo))
 */
public function beforeNewInstanceOfFoo(Joinpoint $jp){]
```

## File ##

This joinpoint is a predicate on the filename where kinded joinpoints can be matched. The signature of this joinpoint is the following:
```
/**
 * @Before(new(*) && file("Foo.php"))
 */
public function beforeAnyInstanceInFoo(Joinpoint $jp){}
```

## Within ##
This joinpoint is a predicate on the class where kinded joinpoints can be matched. The signature of this joinpoint is the following:
```
/**
 * @Before(new(*) && within(Foo))
 */
public function beforeAnyInstanceWithinFoo(Joinpoint $jp){}
```

# Pointcuts #

Pointcuts compose joinpoints like predicates, using: || (or), && (and) and ! (not).

In the following example, we intercept all constructions of an Account instance and all executions of the methods from the Account class except the login method:
```
/**
 * @Before(new(Account) || exec(Account::*) && !exec(Account::login))
 */
public function beforeAnyInstanceWithinFoo(Joinpoint $jp){}
```