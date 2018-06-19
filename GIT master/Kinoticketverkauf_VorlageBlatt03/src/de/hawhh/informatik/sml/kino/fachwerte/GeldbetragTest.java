package de.hawhh.informatik.sml.kino.fachwerte;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class GeldbetragTest
{

    @Test
    public final void testGeldbetrag()
    {
        Geldbetrag betrag = Geldbetrag.get(100);
        assertEquals(1, betrag.getEuroAnteil());
        assertEquals(0, betrag.getCentAnteil());
        assertEquals("1,00", betrag.getFormatiertenString());

        betrag = Geldbetrag.get(0);
        assertEquals(0, betrag.getEuroAnteil());
        assertEquals(0, betrag.getCentAnteil());
        assertEquals("0,00", betrag.getFormatiertenString());

        betrag = Geldbetrag.get(99);
        assertEquals(0, betrag.getEuroAnteil());
        assertEquals(99, betrag.getCentAnteil());
        assertEquals("0,99", betrag.getFormatiertenString());

        betrag = Geldbetrag.get(101);
        assertEquals(1, betrag.getEuroAnteil());
        assertEquals(1, betrag.getCentAnteil());
        assertEquals("1,01", betrag.getFormatiertenString());
    }

    @Test
    public final void testEqualsHashcode()
    {
        Geldbetrag betrag1 = Geldbetrag.get(100);
        Geldbetrag betrag2 = Geldbetrag.get(100);
        assertTrue(betrag1.equals(betrag2));
        assertTrue(betrag1.hashCode() == betrag2.hashCode());

        Geldbetrag betrag3 = Geldbetrag.get(101);
        assertFalse(betrag1.equals(betrag3));
        assertFalse(betrag1.hashCode() == betrag3.hashCode());

        Geldbetrag betrag4 = Geldbetrag.get(1000);
        assertFalse(betrag1.equals(betrag4));
        assertFalse(betrag1.hashCode() == betrag4.hashCode());
    }
    
    @Test
	public void testBetraegeSubtrahieren()
	{
		Geldbetrag g1 = Geldbetrag.get(300000);
		Geldbetrag g2 = Geldbetrag.get(250000);
		Geldbetrag g3 = Geldbetrag.get(350000);
		Geldbetrag g4 = Geldbetrag.get(987654321);
		Geldbetrag g5 = Geldbetrag.get(123456789);
		Geldbetrag g6 = Geldbetrag.get(300046);
		Geldbetrag g7 = Geldbetrag.get(200046);
		
		assertEquals(500, g1.minusGeldbetrag(g2).getEuroAnteil());
		assertEquals(-500, g1.minusGeldbetrag(g3).getEuroAnteil());
		assertEquals(0, g1.minusGeldbetrag(g1).getEuroAnteil());
		assertEquals(8641975, g4.minusGeldbetrag(g5).getEuroAnteil());
		assertEquals(0, g1.minusGeldbetrag(g2).getCentAnteil());
		assertEquals(0, g1.minusGeldbetrag(g3).getCentAnteil());
		assertEquals(0, g1.minusGeldbetrag(g1).getCentAnteil());
		assertEquals(32, g4.minusGeldbetrag(g5).getCentAnteil());
		assertEquals(-46, g1.minusGeldbetrag(g6).getCentAnteil());
		assertEquals(999, g1.minusGeldbetrag(g7).getEuroAnteil());
		assertEquals(54, g1.minusGeldbetrag(g7).getCentAnteil());
	}
	
	@Test
	public void testBetraegeAddieren()
	{
		Geldbetrag g1 = Geldbetrag.get(300000);
		Geldbetrag g2 = Geldbetrag.get(250000);
		Geldbetrag g3 = Geldbetrag.get(350000);
		Geldbetrag g4 = Geldbetrag.get(987654321);
		Geldbetrag g5 = Geldbetrag.get(123456789);
		Geldbetrag g6 = Geldbetrag.get(300046);
		
		assertEquals(5500, g1.plusGeldbetrag(g2).getEuroAnteil());
		assertEquals(6500, g1.plusGeldbetrag(g3).getEuroAnteil());
		assertEquals(6000, g1.plusGeldbetrag(g1).getEuroAnteil());
		assertEquals(11111111, g4.plusGeldbetrag(g5).getEuroAnteil());
		assertEquals(0, g1.plusGeldbetrag(g2).getCentAnteil());
		assertEquals(0, g1.plusGeldbetrag(g3).getCentAnteil());
		assertEquals(0, g1.plusGeldbetrag(g1).getCentAnteil());
		assertEquals(10, g4.plusGeldbetrag(g5).getCentAnteil());
		assertEquals(46, g1.plusGeldbetrag(g6).getCentAnteil());
	}
	
	@Test
	public void testBetraegeMultiplizieren()
	{
		Geldbetrag g1 = Geldbetrag.get(300000);
		Geldbetrag g2 = Geldbetrag.get(250000);
		Geldbetrag g3 = Geldbetrag.get(350000);
		Geldbetrag g4 = Geldbetrag.get(9876521);
		Geldbetrag g5 = Geldbetrag.get(1256789);
		Geldbetrag g6 = Geldbetrag.get(300046);
		
		assertEquals(3000, g1.multipliziereGeldbetrag(1).getEuroAnteil());
		assertEquals(0, g2.multipliziereGeldbetrag(0).getEuroAnteil());
		assertEquals(9000, g1.multipliziereGeldbetrag(3).getEuroAnteil());
		assertEquals(395060, g4.multipliziereGeldbetrag(4).getEuroAnteil());
		assertEquals(0, g3.multipliziereGeldbetrag(1).getCentAnteil());
		assertEquals(0, g1.multipliziereGeldbetrag(0).getCentAnteil());
		assertEquals(23, g5.multipliziereGeldbetrag(7).getCentAnteil());
		assertEquals(68, g4.multipliziereGeldbetrag(8).getCentAnteil());
		assertEquals(14, g6.multipliziereGeldbetrag(9).getCentAnteil());
	}
	
	@Test
	public void testUmwandeln()
	{
		Geldbetrag g4 = Geldbetrag.get("50,50");
		Geldbetrag g5 = Geldbetrag.get("153,12");
		Geldbetrag g6 = Geldbetrag.get("1749,1");
		Geldbetrag g1 = Geldbetrag.get("17,");
		Geldbetrag g2 = Geldbetrag.get("18");
		Geldbetrag g3 = Geldbetrag.get("17,22654");
		Geldbetrag g7 = Geldbetrag.get("18,334");
		Geldbetrag g8 = Geldbetrag.get("");
		Geldbetrag g9 = Geldbetrag.get("srhs");
		Geldbetrag g10 = Geldbetrag.get("srhs,aga");
		Geldbetrag g11 = Geldbetrag.get("srhs,56");
		Geldbetrag g12 = Geldbetrag.get("15,aga");
		
		assertEquals(50, g4.getEuroAnteil());
		assertEquals(50, g4.getCentAnteil());
		assertEquals(153, g5.getEuroAnteil());
		assertEquals(12, g5.getCentAnteil());
		assertEquals(1749, g6.getEuroAnteil());
		assertEquals(10, g6.getCentAnteil());
		assertEquals(17, g1.getEuroAnteil());
		assertEquals(0, g1.getCentAnteil());
		assertEquals(18, g2.getEuroAnteil());
		assertEquals(0, g2.getCentAnteil());
		assertEquals(22, g3.getCentAnteil());
		assertEquals(33, g7.getCentAnteil());
		assertEquals(0, g8.getEuroAnteil());
		assertEquals(0, g8.getCentAnteil());
		assertEquals(0, g9.getEuroAnteil());
		assertEquals(0, g9.getCentAnteil());
		assertEquals(0, g10.getEuroAnteil());
		assertEquals(0, g10.getCentAnteil());
		assertEquals(0, g11.getEuroAnteil());
		assertEquals(56, g11.getCentAnteil());
		assertEquals(15, g12.getEuroAnteil());
		assertEquals(0, g12.getCentAnteil());
		
	}
}
