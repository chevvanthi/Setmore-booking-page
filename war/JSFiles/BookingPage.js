
$(document).ready(function(){	
					
		 
		 $.ajax({
				
				url         :  '/service',
				type        :   'GET',
				dataType    :   'json',
				success     :  function(result){
								console.log("returned data" + JSON.stringify(result));
														
								$.each(result, function(key,value){
							 		
									
									 var service       = value.services;
									 var serviceNameul = document.createElement('ul');	
									 serviceNameul.setAttribute('class','serviceNameul');
									 
									 console.log("the result value is " + service);
									 
									 $.each(service,function(k,v){			
										 console.log('the service value inside the each loop' + service);
										 
										 var serviceName        = v.service_name;	
										 serviceDuration        = v.duration;
										 serviceCost            = v.cost;
										 serviceKey             = v.key;
										 staffKeys            = v.staff_keys;

										 console.log("the value is " + JSON.stringify(serviceName)  +"   " + JSON.stringify(serviceDuration) + " " + JSON.stringify(serviceCost) + "service key" + JSON.stringify(serviceKey)); 
										
										   
										    var serviceli = document.createElement("li");
										    serviceli.setAttribute('class','serviceLiClass');
										    
										    var servNameSpan = document.createElement('span');
										    servNameSpan.setAttribute('class','servNameSpan');
										    servNameSpan.appendChild(document.createTextNode(serviceName));
										    serviceli.appendChild(servNameSpan);
										    
										   
										   
										   				    		  			    
										    var servDurSpan = document.createElement('span');
										    servDurSpan.setAttribute('class','serviceDurSpan')
										    servDurSpan.appendChild(document.createTextNode(serviceDuration + "mins"));
										    serviceli.appendChild(servDurSpan);
										  			
										    
										    var serCostSpan = document.createElement('span');
										    serCostSpan.setAttribute('class','serCostSpan');
										    serCostSpan.appendChild(document.createTextNode(serviceCost + "rs"));
										    serviceli.appendChild(serCostSpan);
										   
										    var serviceDiv  = document.getElementById('serviceList');		
										    serviceNameul.appendChild(serviceli);
										    serviceDiv.append(serviceNameul);
										    
									 });
									 
									   		    
									  
								});
				},
				failure     :  function(data){
					           console.log('error function ' + data);
				}
			 
				
			});	
	
	
});