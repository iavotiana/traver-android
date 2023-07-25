package fr.iavotiana.travel.model;

import junit.framework.TestCase;

public class ProfilTest extends TestCase {

    private Profil profil = new Profil(67,165,35,0);
    private float img = (float) 83;
    private String message = "Trop grosse";

    public void testGetImg() {
        assertEquals(img, profil.getImg(), (float)0.1);
    }

    public void testGetMessage() {
        assertEquals(message, profil.getMessage());
    }
}