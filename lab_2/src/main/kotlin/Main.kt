fun main() {
    println("Mobile")
    val phone = MobilePhone("Светлана", "89617487985")
    phone.addContact(Contact("Мария", "89615533974"))
    phone.addContact(Contact("Алекс", "89616665555"))
    phone.addContact(Contact("Олег", "89613473985"))
    phone.printContacts()
    print("\n")

    phone.updateContact(Contact("Мария", "89615533974"), Contact("Маша", "89615533977"))
    phone.updateContact(Contact("Алекс", "89616665555").copy(name = "Александр"))
    phone.removeContact(Contact("Олег", "89613473985"))
    phone.printContacts()
    println("index = ${phone.findContact(Contact("Александр", "89616665555"))}")
    println("${phone.queryContact("Маша")}")
}