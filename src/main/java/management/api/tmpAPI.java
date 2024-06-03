package management.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import management.DTO.KhuyenMaiDto;
import management.dao.IKhuyenMaiDAO;


@RestController
@RequestMapping("/api")
public class tmpAPI {
	
	@Autowired 
	private IKhuyenMaiDAO iKhuyenMaiDAO;
	
	
	@GetMapping("/ctdkm")
	public List<KhuyenMaiDto> getCTDKM(@RequestParam("id") int madkm){	
		
		
		return iKhuyenMaiDAO.get_CTDKM_By_MaKM(madkm);
		
	}
	
	
}
