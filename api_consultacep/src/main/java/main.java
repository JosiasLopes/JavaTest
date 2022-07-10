import com.api.v1.consultacep.entity.Endereco;
import com.api.v1.consultacep.utils.calc.CalcFrete;
import org.springframework.web.client.RestTemplate;

public class main {
    public static void main(String[] args){
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://viacep.com.br/ws/06867450/json/";
        String uri2 = "http://viacep.com.br/ws/11432505/json/";

        Endereco origem = restTemplate.getForObject(uri, Endereco.class);
        Endereco destino = restTemplate.getForObject(uri2, Endereco.class);

        System.out.println("DDD da origem");
        System.out.println(origem.getDDD());
        System.out.println("DDD do destino");
        System.out.println(destino.getDDD());





        System.out.println(CalcFrete.calcularFrete(10,origem,destino));

    }
}
