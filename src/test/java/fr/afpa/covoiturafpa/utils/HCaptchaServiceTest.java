package fr.afpa.covoiturafpa.utils;

import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.afpa.covoiturafpa.Application;
import fr.afpa.covoiturafpa.utils.captcha.HCaptchaService;
import fr.afpa.covoiturafpa.utils.captcha.HCaptchaToken;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class HCaptchaServiceTest {

    @Autowired
    private HCaptchaService hCaptchaService;

    @Test
    public void should_return_token_invalidity() {
        hCaptchaService.setCaptchaToken(new HCaptchaToken("10000000-aaaa-bbbb-cccc-000000000001"));
        assertFalse(hCaptchaService.isValid());
    }
}
