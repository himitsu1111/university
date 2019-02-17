import com.mycrud.controller.FacService;
import com.mycrud.model.Faculty;
import config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

import java.util.List;

/**
 * Created by book on 14.02.2019.
 */

@WebAppConfiguration

@ContextConfiguration(classes = {TestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class FacServiceTest {

    @Autowired
    private FacService facService;


    @Test
    public void listPositive() {
        List<Faculty> faculties =  facService.getAllFaculty();
        assertNotNull(faculties);
    }

}
