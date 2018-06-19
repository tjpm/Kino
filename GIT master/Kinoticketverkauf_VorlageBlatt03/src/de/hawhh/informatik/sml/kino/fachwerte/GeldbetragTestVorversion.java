package de.hawhh.informatik.sml.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GeldbetragTestVorversion
{

	public GeldbetragTestVorversion()
	{
		
	}
	
	
	@Test
	public void testKonstruktor()
	{
		Geldbetrag g1 = Geldbetrag.get(654351);
		assertEquals(6543, g1.getEuroAnteil());
		assertEquals(51, g1.getCentAnteil());
		
				
	}
	
	@Test
	public void testEquals()
	{
		Geldbetrag g1 = Geldbetrag.get(654351);
		Geldbetrag g2 = Geldbetrag.get(54351);
		Geldbetrag g3 = Geldbetrag.get(654351);
		
		assertFalse(g1.equals(g2));
		assertFalse(g2.equals(g1));
		assertTrue(g1.equals(g3));
		assertTrue(g3.equals(g1));
		
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
		
		assertEquals(500, g1.minusGeldbetrag(g2).getEuroAnteil());
		assertEquals(-500, g1.minusGeldbetrag(g3).getEuroAnteil());
		assertEquals(0, g1.minusGeldbetrag(g1).getEuroAnteil());
		assertEquals(8641975, g4.minusGeldbetrag(g5).getEuroAnteil());
		assertEquals(0, g1.minusGeldbetrag(g2).getCentAnteil());
		assertEquals(0, g1.minusGeldbetrag(g3).getCentAnteil());
		assertEquals(0, g1.minusGeldbetrag(g1).getCentAnteil());
		assertEquals(32, g4.minusGeldbetrag(g5).getCentAnteil());
		assertEquals(-46, g1.minusGeldbetrag(g6).getCentAnteil());
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
}
