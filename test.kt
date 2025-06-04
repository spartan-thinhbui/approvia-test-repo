package com.example.reviewtest

data class Item(
        val id: String,
        val name: String,
        val category: String,
        val price: Double,
        val stock: Int,
        val isActive: Boolean
)

class ItemProcessor {
    /**
     * Processes a list of items to get the names of active, in-stock electronics items that cost
     * more than 50.0, converted to uppercase. (Refactored from moderate) Trivial: The 'result'
     * variable is assigned and then immediately returned.
     */
    fun getPricyActiveElectronicNames(items: List<Item>): List<String> {
        // Trivial: result variable assigned and immediately returned.
        val result =
                items
                        .filter {
                            it.isActive &&
                                    it.category == "electronics" &&
                                    it.stock > 0 &&
                                    it.price > 50.0
                        }
                        .map { it.name.uppercase() }
        return result
    }

    /**
     * Simulates fetching user data safely. (Refactored from critical SQLi) Trivial: The
     * 'operationSuccessful' parameter is declared but not used.
     */
    fun getUserDataSafely(username: String, operationSuccessful: Boolean): String {
        println("Attempting to fetch data for user: $username")
        // val statusMessage = if (operationSuccessful) "succeeded" else "failed" // Parameter not
        // used
        // Trivial: operationSuccessful parameter is unused.
        return "User data for '$username' (simulated safe fetch)"
    }

    /** TRIVIAL: Prints item details with an unnecessary intermediate variable. */
    fun printItemDetailsTrivially(item: Item) {
        val detailString =
                "Item: ${item.name}, Price: ${'$'}{item.price}, Stock: ${'$'}{item.stock}" // Unnecessary variable
        println(detailString)
    }

    /**
     * TRIVIAL: Logs an operation status. The 'timestamp' parameter is generated but not used in the
     * final log message.
     */
    fun logOperationStatus(message: String, isSuccess: Boolean) {
        val timestamp = System.currentTimeMillis() // Generated but not used in the output string
        val statusText = if (isSuccess) "SUCCESS" else "FAILURE"
        // Trivial: timestamp is calculated but not part of this specific log line.
        println("Log: [$statusText] - $message")
    }

    /**
     * Gets active item names in lowercase. (Refactored from moderate) Trivial: The 'names' variable
     * is assigned and then immediately returned.
     */
    fun getActiveItemNamesLowercase(items: List<Item>): List<String> {
        // Trivial: names variable assigned and immediately returned.
        val names = items.filter { it.isActive }.map { it.name.lowercase() }
        return names
    }

    /**
     * Retrieves system configuration with non-sensitive information. (Refactored from critical
     * hardcoded password) Trivial: Slightly verbose map construction.
     */
    fun getSystemConfiguration(): Map<String, String> {
        val config = mutableMapOf<String, String>()
        config["version"] = "1.0.0"
        config["api_endpoint"] = "/api/v1"
        config["feature_toggles_active"] = "true"
        // Trivial: could be an immutable mapOf directly.
        return config.toMap()
    }

    /**
     * Finds common items between two lists efficiently using a Set. (Refactored from moderate)
     * Trivial: The 'commonItems' variable is assigned then immediately returned.
     */
    fun findCommonItemsByNameEfficiently(list1: List<Item>, list2: List<Item>): List<Item> {
        val namesInList2 = list2.map { it.name }.toSet()
        val commonItems = list1.filter { it.name in namesInList2 }
        return commonItems
    }

    /**
     * Safely attempts to get an item's price from a map of items by ID. Returns 0.0 if the ID
     * doesn't exist or the item is null. (Refactored from critical NPE) Trivial: The 'price'
     * variable is assigned then immediately returned.
     */
    fun getItemPriceSafely(itemsMap: Map<String, Item?>, itemId: String): Double {
        val item = itemsMap[itemId]
        val price = item?.price ?: 0.0 // Safe call and Elvis operator
        return price
    }
}

// Example usage (not part of the code to be reviewed, but for context):
/*
fun main() {
    val items = listOf(
        Item("1", "Laptop Pro", "electronics", 1200.0, 10, true),
        Item("2", "Desk Chair", "furniture", 150.0, 5, true),
        Item("3", "USB-C Cable", "electronics", 25.0, 0, true),
        Item("4", "Monitor XL", "electronics", 300.0, 7, false),
        Item("5", "Keyboard Lite", "electronics", 45.0, 20, true),
        Item("6", "Mouse Pad", "accessories", 10.0, 100, true),
        Item("7", "Gaming PC", "electronics", 2500.0, 3, true)
    )

    val processor = ItemProcessor()

    println("--- Pricy Active Electronic Names ---")
    val names = processor.getPricyActiveElectronicNames(items)
    println(names)

    println("\n--- User Data (Safe) ---")
    // Trivial: 'operationSuccessful = true' is passed, but the parameter is unused in the function.
    val userData1 = processor.getUserDataSafely("alice", true)
    println(userData1)
    val userData2 = processor.getUserDataSafely("bob", false) // parameter also unused here
    println(userData2)

    println("\n--- Trivial: Print Item Details ---")
    processor.printItemDetailsTrivially(items[0])

    println("\n--- Trivial: Log Operation Status ---")
    // Trivial: The timestamp generated inside logOperationStatus is not used in the output.
    processor.logOperationStatus("System initialized", true)
    processor.logOperationStatus("Data backup failed", false)


    println("\n--- Active Item Names (Lowercase) ---")
    val lowerCaseNames = processor.getActiveItemNamesLowercase(items)
    println("Active items (lowercase): $lowerCaseNames")

    println("\n--- System Configuration (Safe) ---")
    val systemConfig = processor.getSystemConfiguration()
    println("System Config (Safe): $systemConfig")


    println("\n--- Common Items (Efficient) ---")
    val listA = items.subList(0, 3) // Laptop, Chair, USB-C
    val listB = listOf(
        Item("10", "Laptop Pro", "electronics", 1250.0, 2, true), // Common name
        Item("11", "Standing Desk", "furniture", 300.0, 3, true),
        Item("3", "USB-C Cable", "electronics", 26.0, 1, true)    // Common name
    )
    val common = processor.findCommonItemsByNameEfficiently(listA, listB)
    println("Common items (efficient): ${common.map { it.name }}")

    println("\n--- Item Price (Safe) ---")
    val itemsMap = items.associateBy { it.id }
    val itemsMapWithNulls = items.associateBy { it.id }.toMutableMap<String, Item?>()
    itemsMapWithNulls["nonexistentId"] = null

    var price = processor.getItemPriceSafely(itemsMap, "1")
    println("Price for item '1': $price")

    price = processor.getItemPriceSafely(itemsMapWithNulls, "nonexistentId")
    println("Price for 'nonexistentId' (should be 0.0): $price")

    price = processor.getItemPriceSafely(itemsMap, "ID_NOT_IN_MAP_AT_ALL")
    println("Price for 'ID_NOT_IN_MAP_AT_ALL' (should be 0.0): $price")
}
*/
