import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Unmarshal {
    public static void main(String[] args) throws JAXBException {

        File file = new File("./invoice.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(generated.Invoice.class);
        Unmarshaller um = jaxbContext.createUnmarshaller();
        generated.Invoice obj = (generated.Invoice) um.unmarshal(file);
    }
}
