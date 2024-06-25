import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MobilePhoneTest {
    private val mobilePhone = MobilePhone("Светлана", "89617487985")

    @Test
    fun addContact() {
        val contact = Contact("Мария", "89615533974")
        assertTrue(mobilePhone.addContact(contact))
        assertFalse(mobilePhone.addContact(contact)) //exist
    }

    @Test
    fun updateContact() {
        val old = Contact("Мария", "89615533974")
        val new = Contact("Мария", "89615533977")
        mobilePhone.addContact(old)
        assertTrue(mobilePhone.updateContact(old, new))
        assertEquals(new, mobilePhone.queryContact("Мария"))
    }

    @Test
    fun removeContact() {
        val contact = Contact("Мария", "89615533974")
        mobilePhone.addContact(contact)
        assertTrue(mobilePhone.removeContact(contact))
        assertNull(mobilePhone.queryContact("Мария"))
    }

    @Test
    fun findContact() {
        val contact = Contact("Мария", "89615533974")
        mobilePhone.addContact(contact)
        assertEquals(0, mobilePhone.findContact(contact))
    }

    @Test
    fun queryContact() {
        val contact = Contact("Мария", "89615533974")
        mobilePhone.addContact(contact)
        assertEquals(contact, mobilePhone.queryContact("Мария"))
        assertNull(mobilePhone.queryContact("Мария"))
    }
}