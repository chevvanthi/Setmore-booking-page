var Model = (function($,window) {

		var makeAjaxRequest = function(request) {

			try {
				var ajaxOptions = {
					'type' 		 : request.type,
					'url'		 : request.url,
					'data'		 : request.data,
					'contentType': request.contentType,
					'dataType' 	 : request.dataType
				};

				if(request.headers) {
					ajaxOptions.headers = request.headers;
				}


				return $.ajax(ajaxOptions);

			} catch (err) {
				console.error("Error at makeAjaxRequest ", err);
			}

		};
		
		return {
			makeAjaxRequest : makeAjaxRequest
		}
})(window);