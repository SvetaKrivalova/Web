data class Contact(val name: String, val phone: String)

class MobilePhone(private val myName: String, private val myPhone: String) {
    private val myContacts: MutableList<Contact> = ArrayList()

    fun addContact(newContact: Contact): Boolean {
        if (myContacts.any { it.phone == newContact.phone }) {
            return false
        }
        myContacts.add(newContact)
        return true
    }

    fun updateContact(oldContact: Contact, newContact: Contact): Boolean {
        val index = myContacts.indexOfFirst { it.phone == oldContact.phone }
        if (index == -1) {
            return false
        }
        myContacts[index] = newContact
        return true
    }

    fun removeContact(contact: Contact): Boolean {
        val index = myContacts.indexOfFirst { it.phone == contact.phone }
        if (index == -1) {
            return false
        }
        myContacts.removeAt(index)
        return true
    }

    fun findContact(contact: Contact): Int {
        return myContacts.indexOfFirst { it.phone == contact.phone }
    }

    fun queryContact(name: String): Contact? {
        return myContacts.firstOrNull { it.name == name }
    }

    fun printContacts() {
        myContacts.forEach { println("${it.name}: ${it.phone}") }
    }

    fun updateContact(newContact: Contact) {
        myContacts.replaceAll {
            if (it.name == newContact.name || it.phone == newContact.phone) newContact
            else it
        }
    }
}