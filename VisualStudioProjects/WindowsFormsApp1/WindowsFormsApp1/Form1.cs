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
            //var compositeRoot = new Composite("composite1");

            //compositeRoot.Add(new ComponentContactGroup("CG_C", null));
            //compositeRoot.Add(new ComponentAbsence("Abs1", null));
            //compositeRoot.Add(new ComponentContactGroup("CG_A", null));
            //compositeRoot.Add(new ComponentAbsence("Abs2", null));
            //compositeRoot.Add(new ComponentContactGroup("CG_B", null));

            //Composite composite2 = new Composite("composite2");
            //composite2.Add(new ComponentInterval("Interval", null));

            //composite2.Add(new ComponentContactGroup("CG_A", null));
            //composite2.Add(new ComponentAbsence("Abs2", null));
            //composite2.Add(new ComponentContactGroup("CG_B", null));

            //compositeRoot.Add(composite2);

            Graphics formGraphics = this.CreateGraphics();
            formGraphics.Clear(Color.BlueViolet);

            //compositeRoot.Draw(formGraphics);
            Composition composition = new Composition("composition", null);

            composition.Add(new ComponentContactGroup("CG_C", null));
            composition.Add(new ComponentAbsence("Abs1", null));
            composition.Add(new ComponentContactGroup("CG_A", null));
            composition.Add(new ComponentAbsence("Abs2", null));
            composition.Add(new ComponentContactGroup("CG_B", null));
                        
            composition.Add(new ComponentInterval("Interval", null));
                        
            composition.Add(new ComponentContactGroup("CG_A", null));
            composition.Add(new ComponentAbsence("Abs2", null));
            composition.Add(new ComponentContactGroup("CG_B", null));

            //composition.Add(compositeRoot);

            composition.Compositor_Compose();

            composition.Draw(formGraphics);

            formGraphics.Dispose();
        }
    }
}
