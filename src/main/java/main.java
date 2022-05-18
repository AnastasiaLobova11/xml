import org.xml.sax.SAXException;

import javax.xml.bind.*;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws JAXBException, IOException, SAXException {

        String s[] = {"bag", "phone", "paper", "pen"};
        int n[] = {2, 1, 4, 100};
        String c[] = {"200.2", "40000.99", "500", "23.5"};

        List<package1.InvoiceItem> arr = new ArrayList<>();

        for (int i = 0; i < 4; ++i) {
            arr.add(new package1.InvoiceItem(c[i], n[i], s[i]));
        }

        package1.Invoice d1 = new package1.Invoice(1234567, package1.Status.PAID, arr);
        System.out.println(d1);

        JAXBContext context = JAXBContext.newInstance(package1.Invoice.class,package1.InvoiceItem.class, package1.Status.class);
        Marshaller mar = context.createMarshaller();
        Unmarshaller um = context.createUnmarshaller();
        File file = new File("./invoice.xml");
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(d1, file);
        package1.Invoice obj = (package1.Invoice) um.unmarshal(file);
        System.out.println(d1.equals(obj));
        System.out.println("Unmarshal:\n" + obj);
        System.out.println(d1.equals(obj));

        context.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(String namespaceURI, String suggestedFileName)
                    throws IOException {
                return new StreamResult(suggestedFileName);
            }
        });
    }
}
