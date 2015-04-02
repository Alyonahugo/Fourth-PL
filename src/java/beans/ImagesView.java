/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ImagesView {

    private List<String> images;

    @PostConstruct
    public void init() {
        images = new ArrayList<String>(7);

        images.add("Brain");
        images.add("Barclis Capital");
        images.add("UBS");
        images.add("Renaissance Credit");
        images.add("Rainbow");
        images.add("Альфа банк");
        images.add("Газпромбанк");  
        images.add("Райффайзенбанк");
    }

    public List<String> getImages() {
        return images;
    }
}
