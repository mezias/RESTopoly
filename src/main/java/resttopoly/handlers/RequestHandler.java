package resttopoly.handlers;


import resttopoly.Answer;

import java.util.Map;

/**
 * @author DucNguyenMinh
 * @since 09/04/16
 */
public interface RequestHandler
{
    Answer process(Map<String,String> urlParams);
}
