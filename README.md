# jasper
Simple data management system to replace default mongo driver

# installation

```gradle
    repositories {
        maven("https://jitpack.io")
    }
    
    dependencies {
        implementation("com.github.matrix-studios-software:jasper:[commit id]")
        implementation("org.mongodb:mongodb-driver-sync:4.9.0")
    }
   
```

# using

```kt
    Jasper.createClient("uri", "database name")
```

```kt
    class YourContainer : JasperContainer<String, Object>(Object::class.java)
```

```kt
    @DataObject
    data class Object(val name: String)
```

