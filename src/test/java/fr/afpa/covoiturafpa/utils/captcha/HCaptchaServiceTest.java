package fr.afpa.covoiturafpa.utils.captcha;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.afpa.covoiturafpa.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class HCaptchaServiceTest {

    @Autowired
    private HCaptchaService hCaptchaService;
    @Autowired
    private HCaptchaConfig hCaptchaConfig;

    @Test
    public void should_return_token_validity() {
        hCaptchaConfig.setSecret("0x0000000000000000000000000000000000000000");
        hCaptchaService.setCaptchaToken(new HCaptchaToken("10000000-aaaa-bbbb-cccc-000000000001"));
        assertTrue(hCaptchaService.isValid());
    }

    @Test
    public void should_return_token_invalidity() {
        hCaptchaService.setCaptchaToken(new HCaptchaToken("10000000-aaaa-bbbb-cccc-000000000002"));
        assertFalse(hCaptchaService.isValid());
    }
}
