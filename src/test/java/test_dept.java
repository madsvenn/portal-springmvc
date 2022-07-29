import com.leozhang.portalssm.service.DeptService;
import com.leozhang.portalssm.service.impl.DeptServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class test_dept {

    private static Logger logger= LogManager.getLogger(LogManager.DEFAULT_CONFIGURATION_FILE);

    @Test
    public void selecttest(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DeptServiceImpl service = context.getBean(DeptServiceImpl.class);
        logger.info(service.selectDeptByNameResult(1,2,null,null,"","",""));
    }
}
