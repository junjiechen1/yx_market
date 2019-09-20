using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;


namespace yx_marketplace
{
    // Learn more about making custom code visible in the Xamarin.Forms previewer
    // by visiting https://aka.ms/xamarinforms-previewer
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
            
           
        }
        
        private void LoginButton_Clicked(object sender, EventArgs e)
        {
            bool IsEmailEmpty = string.IsNullOrEmpty(EmailEntry.Text);
            bool IsPasswordEmpty = string.IsNullOrEmpty(PasswordEntry.Text);
            if(IsEmailEmpty == true ||IsPasswordEmpty ==true)
            {

            }
            else
            {
                Navigation.PushAsync(new HomePage());
            }
        }
    }
}
