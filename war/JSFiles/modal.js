	var Model = (function($,window,document) {

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
					ajaxOptions.beforeSend = function(xhr) {
						for(var key in request.headers)
							xhr.setRequestHeader(key, request.headers[key]);
					}
				}

				if(request.headers) {
					ajaxOptions.headers = request.headers;
				}


				return $.ajax(ajaxOptions);

			} catch (err) {
				console.error("Error at makeAjaxRequest ", err);
			}

		};

		return {
			makeAjaxRequest: makeAjaxRequest,
		}

	})(jQuery,window);
