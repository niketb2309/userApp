function restrictBackForLogout() {
   history.pushState(null, null, '');
              window.addEventListener('popstate', function(event) {
                  history.pushState(null, null, '');
              });
          history.replaceState({}, null, "/");
}