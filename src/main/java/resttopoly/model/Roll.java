package resttopoly.model;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.Data;
/**
 * @author DucNguyenMinh
 * @since 09/04/16
 */
@Data
public class Roll
{
    private int number;

    public Roll(int number)
    {
        this.number = number;
    }
}
