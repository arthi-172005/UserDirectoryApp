package com.example.userdirectory

class UserRepository {
    private val apiService = ApiClient.apiService
    private val mockUsers = mutableListOf<User>()
    
    init {
        // Initialize with some mock data as fallback
        initializeMockData()
    }
    
    suspend fun getUsers(): List<User> {
        return try {
            val response = apiService.getUsers()
            if (response.isSuccessful) {
                response.body() ?: getMockUsers()
            } else {
                getMockUsers()
            }
        } catch (e: Exception) {
            // Fallback to mock data if API fails
            getMockUsers()
        }
    }
    
    suspend fun addUser(user: User): User {
        return try {
            val response = apiService.addUser(user)
            if (response.isSuccessful) {
                response.body() ?: user
            } else {
                // Add to mock data and return
                val newUser = user.copy(id = generateNewId())
                mockUsers.add(newUser)
                newUser
            }
        } catch (e: Exception) {
            // Add to mock data and return
            val newUser = user.copy(id = generateNewId())
            mockUsers.add(newUser)
            newUser
        }
    }
    
    private fun getMockUsers(): List<User> {
        if (mockUsers.isEmpty()) {
            initializeMockData()
        }
        return mockUsers.toList()
    }
    
    private fun initializeMockData() {
        mockUsers.clear()
        mockUsers.addAll(
            listOf(
                User(
                    id = 1,
                    name = "Leanne Graham",
                    email = "sincere@april.biz",
                    phone = "1-770-736-8031 x56442",
                    address = Address(
                        street = "Kulas Light",
                        suite = "Apt. 556",
                        city = "Gwenborough",
                        zipcode = "92998-3874"
                    ),
                    website = "hildegard.org",
                    company = Company(name = "Romaguera-Crona")
                ),
                User(
                    id = 2,
                    name = "Ervin Howell",
                    email = "shanna@melissa.tv",
                    phone = "010-692-6593 x09125",
                    address = Address(
                        street = "Victor Plains",
                        suite = "Suite 879",
                        city = "Wisokyburgh",
                        zipcode = "90566-7771"
                    ),
                    website = "anastasia.net",
                    company = Company(name = "Deckow-Crist")
                ),
                User(
                    id = 3,
                    name = "Clementine Bauch",
                    email = "nathan@yesenia.net",
                    phone = "1-463-123-4447",
                    address = Address(
                        street = "Douglas Extension",
                        suite = "Suite 847",
                        city = "McKenziehaven",
                        zipcode = "59590-4157"
                    ),
                    website = "ramiro.info",
                    company = Company(name = "Romaguera-Jacobson")
                ),
                User(
                    id = 4,
                    name = "Patricia Lebsack",
                    email = "julianne.oconner@kory.org",
                    phone = "493-170-9623 x156",
                    address = Address(
                        street = "Hoeger Mall",
                        suite = "Apt. 692",
                        city = "South Elvis",
                        zipcode = "53919-4257"
                    ),
                    website = "kale.biz",
                    company = Company(name = "Robel-Corkery")
                ),
                User(
                    id = 5,
                    name = "Chelsey Dietrich",
                    email = "lucio_hettinger@annie.ca",
                    phone = "(254)954-1289",
                    address = Address(
                        street = "Skiles Walks",
                        suite = "Suite 351",
                        city = "Roscoeview",
                        zipcode = "33263"
                    ),
                    website = "demarco.info",
                    company = Company(name = "Keebler LLC")
                )
            )
        )
    }
    
    private fun generateNewId(): Int {
        return (mockUsers.maxOfOrNull { it.id } ?: 0) + 1
    }
}
