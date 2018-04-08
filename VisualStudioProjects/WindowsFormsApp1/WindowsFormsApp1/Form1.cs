using CompositeClassLibrary;
using CompositeClassLibrary.CompositePattern;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();           
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var compositeRoot = new Composite("composite1");

            ComponentNewLine componentLine = new ComponentNewLine("line");
            //componentLine.Add(new ComponentText());
            //componentLine.Add(new ComponentText());

            compositeRoot.Add(componentLine);
            
            compositeRoot.Add(new ComponentText("text"));
            compositeRoot.Add(new ComponentNewLine("line x"));

            Composite composite2 = new Composite("composite2");
            ComponentNewLine componentLine2 = new ComponentNewLine("line2");
            composite2.Add(componentLine2);

            composite2.Add(new ComponentInterval("Interval", null));

            compositeRoot.Add(composite2);

            Graphics formGraphics = this.CreateGraphics();
            formGraphics.Clear(Color.BlueViolet);

            //compositeRoot.Draw(formGraphics);
            Composition composition = new Composition("composition", null);

            composition.Add(compositeRoot);

            composition.Compose();

            composition.Draw(formGraphics);

            formGraphics.Dispose();
        }
    }
}
