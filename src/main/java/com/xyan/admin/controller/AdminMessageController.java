package com.xyan.admin.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xyan.blog.model.MessageModel;
import com.xyan.blog.service.MessageService;
import com.xyan.frame.base.web.ResponseModel;

@Controller
@RequestMapping("admin/message")
public class AdminMessageController {
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView("admin/message/message");
	}
	
	@RequestMapping(value="del{id}",method=RequestMethod.GET)
	public String del(@PathVariable Long id,HttpServletRequest request){
		messageService.delete(id);
		MessageModel message=new MessageModel();
		message.setRead("false");
		List<HashMap<String, Object>> messageList=messageService.selectByExample(message);
		request.getSession().getServletContext().setAttribute("messageList",messageList );
		request.getSession().getServletContext().setAttribute("messageSize",messageList.size());
		return "redirect:/admin/message";
	}
	
	@RequestMapping("read{id}")
	@ResponseBody
	public ResponseModel getshowMess(@PathVariable Long id,HttpServletRequest request){
		MessageModel model=new MessageModel();
		model.setId(id);
		model.setRead("true");
		messageService.update(model);
		
		MessageModel message=new MessageModel();
		message.setRead("false");
		List<HashMap<String, Object>> messageList=messageService.selectByExample(message);
		request.getSession().getServletContext().setAttribute("messageList",messageList );
		request.getSession().getServletContext().setAttribute("messageSize",messageList.size());
		
		return new ResponseModel(true);
	}
}
