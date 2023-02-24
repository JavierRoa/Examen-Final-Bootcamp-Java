package cl.aiep.practicafinal.webcontroller;

import cl.aiep.practicafinal.dao.CursoDao;
import cl.aiep.practicafinal.dao.RegionDao;
import cl.aiep.practicafinal.dao.UsuarioDao;
import cl.aiep.practicafinal.entities.Curso;
import cl.aiep.practicafinal.entities.Usuario;
import cl.aiep.practicafinal.security.UserSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;

/* El WebController, es la clase que permite (como dice su nombre) controlar los aspectos Web de la página.
En este caso, eso sería asignar las URL a las distintas vistas en los GetMapping, además del comportamiento
y transferencia de datos en los PostMapping
 */

@Controller
public class WebController {

    @ModelAttribute("cCurso")
    public Curso newCurso() {
        return new Curso();
    }

    @ModelAttribute("cUsuario")
    public Usuario newUsuario() {
        return new Usuario();
    }

    // Hacemos Autowired para poder llamar los métodos de los Daos en los casos necesarios
    @Autowired
    private RegionDao regionDao;

    @Autowired
    private CursoDao cursoDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }
    @GetMapping("")
    public String home(Usuario usuario, Model model) {
        model.addAttribute("usuario", usuario);
        return "general/home";
    }
    @GetMapping("/courses/{id}")
    public String descripcion(Authentication usuarioAuth, Model model, @PathVariable Long id) {
        UserSec userSec = (UserSec) usuarioAuth.getPrincipal();
        Usuario usuario = usuarioDao.buscar(userSec.getUsuario().getId());
        model.addAttribute("authId", usuario.getCurso());
        model.addAttribute("curso", cursoDao.buscar(id));
        return "general/coursedetails";
    }
    @GetMapping("/courses2/{id}")
    public String descripcion(Model model, @PathVariable Long id) {
        model.addAttribute("curso", cursoDao.buscar(id));
        return "general/coursedetails";
    }
    @GetMapping("/courses")
    public String cursos(Authentication usuarioAuth, Model model) {
        UserSec userSec = (UserSec) usuarioAuth.getPrincipal();
        Usuario usuario = usuarioDao.buscar(userSec.getUsuario().getId());
        model.addAttribute("authId", usuario.getCurso());
        model.addAttribute("cursos", cursoDao.listar());
        return "/general/availablecourses";
    }
    @GetMapping("/courses2")
    public String cursosAdmin(Model model) {
        model.addAttribute("cursos", cursoDao.listar());
        return "/general/availablecourses";
    }
    @GetMapping("/cpannel")
    public String cPannel(Authentication usuarioAuth, Model model) {
        UserSec userSec = (UserSec) usuarioAuth.getPrincipal();
        Usuario usuario = usuarioDao.buscar(userSec.getUsuario().getId());
        model.addAttribute("curso", usuario.getCurso());
        return "general/pannel";
    }
    @GetMapping("/course/creation")
    public String creacionCurso() {
        return "forms/coursecreation";
    }
    @GetMapping("/user/register")
    public String registroUsuario(Model model) {
        model.addAttribute("lista", regionDao.listar());
        return "forms/register";
    }

    @PostMapping("/user/register")
    public String crearUsuario(@Valid Usuario usuario,
                               BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("lista", regionDao.listar());
            return "forms/register";
        }
        else {
            usuarioDao.agregar(usuario);
            return "general/home";
        }
    }

    @PostMapping("/course/register")
    public String crearCurso(@Valid @ModelAttribute("cCurso") Curso curso,
                             BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "forms/coursecreation";
        }
        else {
            if(curso.getFechaInicio().compareTo(curso.getFechaTermino())>0) {
                model.addAttribute("mensaje", "Fecha de inicio mayor a la de termino");
                return "forms/coursecreation";
            }
            cursoDao.agregar(curso);
            return "general/success";
        }
    }

    @PostMapping("/course/success")
    public String inscribir(@Valid Usuario usuario, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "/general/availablecourses";
        }
        else {
            return "general/home";
        }
    }

    @PostMapping("/course/delete/{id}")
    public String eliminarCurso(@PathVariable Long id) {
        cursoDao.eliminar(id);
        return "general/success";
    }
    @PostMapping("/courses2/course/delete/{id}")
    public String eliminacionCurso(@PathVariable Long id) {
        cursoDao.eliminar(id);
        return "general/success";
    }

    @PostMapping("/course/edit/{id}")
    public String editarCurso(@PathVariable Long id, Model model) {
        model.addAttribute("curso", cursoDao.buscar(id));
        return "forms/courseedit";
    }

    @PostMapping("courses2/course/edit/{id}")
    public String edicionCurso(@PathVariable Long id, Model model) {
        model.addAttribute("curso", cursoDao.buscar(id));
        return "forms/courseedit";
    }

    @PostMapping("/course/edit/success/{id}")
    public String editarExitoso(@PathVariable Long id, Curso curso) {
        cursoDao.actualizar(curso);
        return "general/success";
    }

    @PostMapping("/courses2/course/edit/success/{id}")
    public String edicionExitosa(@PathVariable Long id, Curso curso) {
        cursoDao.actualizar(curso);
        return "general/success";
    }

    @PostMapping("/apply/{id}")
    public String postularExitoso(@PathVariable Long id, Authentication usuarioAuth) {
        if(usuarioAuth==null) {
            return "security/login";
        }
        else {
            Curso curso = cursoDao.buscar(id);
            UserSec userSec = (UserSec) usuarioAuth.getPrincipal();
            Usuario usuario = usuarioDao.buscar(userSec.getUsuario().getId());
            usuario.setCurso(curso);
            curso.setCuposRestantes(curso.getCuposRestantes()-1);
            cursoDao.actualizar(curso);
            usuarioDao.actualizar(usuario);
        }
        return "general/success";
    }

    @PostMapping("/courses2/apply/{id}")
    public String postulacionExitosa(@PathVariable Long id, Authentication usuarioAuth) {
        if(usuarioAuth==null) {
            return "security/login";
        }
        else {
            Curso curso = cursoDao.buscar(id);
            UserSec userSec = (UserSec) usuarioAuth.getPrincipal();
            Usuario usuario = usuarioDao.buscar(userSec.getUsuario().getId());
            usuario.setCurso(curso);
            curso.setCuposRestantes(curso.getCuposRestantes()-1);
            cursoDao.actualizar(curso);
            usuarioDao.actualizar(usuario);
        }
        return "general/success";
    }
}




