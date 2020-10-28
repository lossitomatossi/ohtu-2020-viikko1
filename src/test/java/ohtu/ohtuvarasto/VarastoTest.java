package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuus() {
        Varasto varasto1 = new Varasto(-20);
        assertEquals(0, varasto1.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void saldollinenVarasto() {
        Varasto v = new Varasto(2.0, 1.0);
        assertEquals(1, v.getSaldo(), vertailuTarkkuus);
        assertEquals(2, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenAlkusaldo() {
        Varasto v = new Varasto(2.0, -1.0);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ylitaysiVarasto() {
        Varasto v = new Varasto(2.0, 5.0);
        assertEquals(2, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldoVarastoNegTilavuus() {
        Varasto v = new Varasto(-2.0, 1.0);
        assertEquals(-2, v.getSaldo(), vertailuTarkkuus);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaaNegatiivinenMaara() {
        varasto.lisaaVarastoon(-20);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaLiikaaVarastoon() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaNegatiivinenMaara() {
        double maara = varasto.otaVarastosta(-20);
        assertEquals(0, maara, vertailuTarkkuus);
    }

    @Test
    public void otaLiikaaVarastosta() {
        varasto.lisaaVarastoon(5);
        double maara = varasto.otaVarastosta(10);
        assertEquals(5, maara, vertailuTarkkuus);
    }

    @Test
    public void varastonPrinttaus() {
        varasto.lisaaVarastoon(3);
        String printti = "saldo = 3.0, vielä tilaa 7.0";
        assertEquals(printti, varasto.toString());
    }

}